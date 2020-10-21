package br.com.igorfersantos.bancodigitalzup.services;

import br.com.caelum.stella.ValidationMessage;
import br.com.caelum.stella.validation.CPFValidator;
import br.com.igorfersantos.bancodigitalzup.controller.ClienteController;
import br.com.igorfersantos.bancodigitalzup.converter.ClienteAdapter;
import br.com.igorfersantos.bancodigitalzup.data.dto.v1.ClienteDTO;
import br.com.igorfersantos.bancodigitalzup.exception.AgeException;
import br.com.igorfersantos.bancodigitalzup.exception.ExistentResourceException;
import br.com.igorfersantos.bancodigitalzup.exception.InvalidFormatException;
import br.com.igorfersantos.bancodigitalzup.data.model.Cliente;
import br.com.igorfersantos.bancodigitalzup.data.util.DateUtils;
import br.com.igorfersantos.bancodigitalzup.repository.ClienteRepository;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    private final CPFValidator cpfValidator = new CPFValidator();

    public Cliente create(ClienteDTO dto) {
        Cliente entity = ClienteAdapter.toEntity(dto);

        List<ValidationMessage> cpfValidationMessages = cpfValidator.invalidMessagesFor(entity.getCpf());

        if (!EmailValidator.getInstance().isValid(entity.getEmail()))
            throw new InvalidFormatException("Formato de e-mail inválido!");

        if (!cpfValidationMessages.isEmpty())
            throw new InvalidFormatException("Formato de CPF inválido!");

        if (!DateUtils.isMaiorDeIdade(entity.getDataNascimento()))
            throw new AgeException("Proibido o cadastro de menores de 18 anos!");

        var clientResult = clienteRepository.findUserByCpf(entity.getCpf());

        if (clientResult.isPresent()) {
            HttpHeaders httpHeaders = ClienteController.getHeadersWithLocationForEndereco(clientResult.get().getId());
            throw new ExistentResourceException("CPF já cadastrado!", httpHeaders, HttpStatus.CREATED);
        }

        clientResult = clienteRepository.findUserByEmail(entity.getEmail());

        if (clientResult.isPresent()) {
            HttpHeaders httpHeaders = ClienteController.getHeadersWithLocationForEndereco(clientResult.get().getId());
            throw new ExistentResourceException("Email já cadastrado!", httpHeaders, HttpStatus.CREATED);
        }


        Cliente savedEntity = null;

        savedEntity = clienteRepository.save(entity);


        return savedEntity;
    }

}
