package br.com.igorfersantos.bancodigitalzup.services;

import br.com.igorfersantos.bancodigitalzup.converter.EnderecoAdapter;
import br.com.igorfersantos.bancodigitalzup.data.dto.v1.EnderecoDTO;
import br.com.igorfersantos.bancodigitalzup.exception.InvalidFormatException;
import br.com.igorfersantos.bancodigitalzup.exception.ResourceNotFoundException;
import br.com.igorfersantos.bancodigitalzup.data.model.Endereco;
import br.com.igorfersantos.bancodigitalzup.data.model.Cliente;
import br.com.igorfersantos.bancodigitalzup.repository.EnderecoRepository;
import br.com.igorfersantos.bancodigitalzup.repository.ClienteRepository;
import br.com.igorfersantos.bancodigitalzup.data.util.cepvalidator.CEPValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EnderecoService {

    @Autowired
    EnderecoRepository enderecoRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Transactional
    public EnderecoDTO create(EnderecoDTO dto, Long id){
        Endereco endereco = EnderecoAdapter.toEntity(dto);

        if (!CEPValidator.isFormatoValido(endereco.getCep()))
            throw new InvalidFormatException("Formato de CEP inválido!");

        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("O Usuário requisitado não está cadastrado!", HttpStatus.UNPROCESSABLE_ENTITY));

        Endereco entity = null;

        if (cliente.getEndereco() == null) {
            entity = enderecoRepository.save(endereco);
            cliente.setEndereco(endereco);
            clienteRepository.updateEndereco(cliente.getEndereco().getId(), cliente.getId());
        }else{
            return EnderecoAdapter.toDTO(enderecoRepository.findById(cliente.getEndereco().getId()).get());
        }

        return EnderecoAdapter.toDTO(entity);
    }

}
