package br.com.igorfersantos.bancodigitalzup.data.dto.v1;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.util.Date;

@Getter @Setter @AllArgsConstructor @EqualsAndHashCode
public class UserDTO implements Serializable {

    private static final long serialVersionUID = -7793778741900173950L;

    @NotBlank(message = "Nome é obrigatório!")
    private String nome;

    @NotBlank(message = "Sobrenome é obrigatório!")
    private String sobrenome;

    @NotBlank(message = "E-mail é obrigatório!")
    @Email
    private String email;

    @NotNull(message = "Data de nascimento é obrigatório!")
    @Past(message = "Data de nascimento precisa ser no passado!")
    private Date dataNascimento;

    @NotBlank(message = "CPF é obrigatório!")
    private String cpf;
}
