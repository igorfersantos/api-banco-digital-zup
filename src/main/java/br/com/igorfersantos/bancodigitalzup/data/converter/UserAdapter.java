package br.com.igorfersantos.bancodigitalzup.data.converter;

import br.com.igorfersantos.bancodigitalzup.data.dto.v1.UserDTO;
import br.com.igorfersantos.bancodigitalzup.model.User;
import br.com.igorfersantos.bancodigitalzup.util.DateUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UserAdapter {

    private static final DateTimeFormatter dateFormatter
            = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static User toEntity(UserDTO dto){
        return new User(dto.getNome(), dto.getSobrenome(), dto.getEmail(), dto.getDataNascimento(), dto.getCpf());
    }

    public static UserDTO toDTO(User user){
        return new UserDTO(user.getId(), user.getNome(), user.getSobrenome(), user.getEmail(), user.getDataNascimento(), user.getCpf());
    }
}
