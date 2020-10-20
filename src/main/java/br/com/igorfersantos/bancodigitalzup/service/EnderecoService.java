package br.com.igorfersantos.bancodigitalzup.service;

import br.com.igorfersantos.bancodigitalzup.data.converter.EnderecoAdapter;
import br.com.igorfersantos.bancodigitalzup.data.dto.v1.EnderecoDTO;
import br.com.igorfersantos.bancodigitalzup.exception.InvalidFormatException;
import br.com.igorfersantos.bancodigitalzup.model.Endereco;
import br.com.igorfersantos.bancodigitalzup.model.User;
import br.com.igorfersantos.bancodigitalzup.repository.EnderecoRepository;
import br.com.igorfersantos.bancodigitalzup.repository.UserRepository;
import br.com.igorfersantos.bancodigitalzup.util.cepvalidator.CEPValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EnderecoService {

    @Autowired
    EnderecoRepository enderecoRepository;

    @Autowired
    UserRepository userRepository;

    @Transactional
    public EnderecoDTO save(EnderecoDTO dto, Long id){
        Endereco endereco = EnderecoAdapter.toEntity(dto);

        if (!CEPValidator.isFormatoValido(endereco.getCep()))
            throw new InvalidFormatException("Formato de CEP inválido!");

        User usuario = userRepository.findById(id).get();
        Endereco entity = null;

        if (usuario.getEndereco() == null) {
            entity = enderecoRepository.save(endereco);
            usuario.setEndereco(endereco);
            userRepository.updateEndereco(usuario.getEndereco().getId(), usuario.getId());
        }else{
            return EnderecoAdapter.toDTO(enderecoRepository.findById(usuario.getEndereco().getId()).get());
        }

        return EnderecoAdapter.toDTO(entity);
    }

}
