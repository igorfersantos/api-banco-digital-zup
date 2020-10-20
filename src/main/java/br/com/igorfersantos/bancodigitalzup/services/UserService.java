package br.com.igorfersantos.bancodigitalzup.services;

import br.com.caelum.stella.ValidationMessage;
import br.com.caelum.stella.validation.CPFValidator;
import br.com.igorfersantos.bancodigitalzup.converter.UserAdapter;
import br.com.igorfersantos.bancodigitalzup.data.dto.v1.UserDTO;
import br.com.igorfersantos.bancodigitalzup.exception.AgeException;
import br.com.igorfersantos.bancodigitalzup.exception.InvalidFormatException;
import br.com.igorfersantos.bancodigitalzup.data.model.User;
import br.com.igorfersantos.bancodigitalzup.repository.UserRepository;
import br.com.igorfersantos.bancodigitalzup.data.util.DateUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    private final CPFValidator cpfValidator = new CPFValidator();

    public UserDTO create(UserDTO dto) {
        User user = UserAdapter.toEntity(dto);

        List<ValidationMessage> cpfValidationMessages = cpfValidator.invalidMessagesFor(user.getCpf());

        if (!EmailValidator.getInstance().isValid(user.getEmail()))
            throw new InvalidFormatException("Formato de e-mail inválido!");

        if (!cpfValidationMessages.isEmpty())
            throw new InvalidFormatException("Formato de CPF inválido!");

        if (!DateUtils.isMaiorDeIdade(user.getDataNascimento()))
            throw new AgeException("Proibido o cadastro de menores de 18 anos!");

        User entityCpf = userRepository.findUserByCpf(user.getCpf());
        User entityEmail = userRepository.findUserByEmail(user.getEmail());

        User savedEntity = null;

        if (entityCpf == null && entityEmail == null) {
            savedEntity = userRepository.save(user);
        } else {
            savedEntity = entityCpf == null ? entityEmail : entityCpf;
        }

        return UserAdapter.toDTO(savedEntity);
    }

}
