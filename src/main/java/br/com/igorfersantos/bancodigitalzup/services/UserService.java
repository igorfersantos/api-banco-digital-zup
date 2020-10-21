package br.com.igorfersantos.bancodigitalzup.services;

import br.com.caelum.stella.ValidationMessage;
import br.com.caelum.stella.validation.CPFValidator;
import br.com.igorfersantos.bancodigitalzup.converter.UserAdapter;
import br.com.igorfersantos.bancodigitalzup.data.dto.v1.ClienteDTO;
import br.com.igorfersantos.bancodigitalzup.data.model.Cliente;
import br.com.igorfersantos.bancodigitalzup.data.util.DateUtils;
import br.com.igorfersantos.bancodigitalzup.exception.AgeException;
import br.com.igorfersantos.bancodigitalzup.exception.InvalidFormatException;
import br.com.igorfersantos.bancodigitalzup.repository.ClienteRepository;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    ClienteRepository clienteRepository;

    private final CPFValidator cpfValidator = new CPFValidator();

    public Cliente create(ClienteDTO dto) {
        Cliente cliente = UserAdapter.toEntity(dto);

        List<ValidationMessage> cpfValidationMessages = cpfValidator.invalidMessagesFor(cliente.getCpf());

        if (!EmailValidator.getInstance().isValid(cliente.getEmail()))
            throw new InvalidFormatException("Formato de e-mail inválido!");

        if (!cpfValidationMessages.isEmpty())
            throw new InvalidFormatException("Formato de CPF inválido!");

        if (!DateUtils.isMaiorDeIdade(cliente.getDataNascimento()))
            throw new AgeException("Proibido o cadastro de menores de 18 anos!");

        Cliente entityCpf = clienteRepository.findUserByCpf(cliente.getCpf());
        Cliente entityEmail = clienteRepository.findUserByEmail(cliente.getEmail());

        Cliente savedEntity = null;

        if (entityCpf == null && entityEmail == null) {
            savedEntity = clienteRepository.save(cliente);
        } else {
            savedEntity = entityCpf == null ? entityEmail : entityCpf;
        }

        return savedEntity;
    }

}
