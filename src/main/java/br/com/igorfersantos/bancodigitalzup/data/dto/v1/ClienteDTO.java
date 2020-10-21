package br.com.igorfersantos.bancodigitalzup.data.dto.v1;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@JsonPropertyOrder({"nome", "sobrenome", "email", "dataNascimento", "cpf"})
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClienteDTO implements Serializable {

    private static final long serialVersionUID = -7793778741900173950L;

    @NotBlank(message = "Nome é obrigatório!")
    private String nome;

    @NotBlank(message = "Sobrenome é obrigatório!")
    private String sobrenome;

    @NotBlank(message = "E-mail é obrigatório!")
    private String email;

    @NotNull(message = "Data de nascimento é obrigatório!")
    @Past(message = "Data de nascimento precisa ser no passado!")
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dataNascimento;

    @NotBlank(message = "CPF é obrigatório!")
    private String cpf;
}
