package br.com.igorfersantos.bancodigitalzup.data.dto.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter @Setter @AllArgsConstructor @EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class EnderecoDTO implements Serializable {

    private static final long serialVersionUID = -8682951928518490969L;

    @NotBlank(message = "Cep é obrigatório!")
    private String cep;

    @NotBlank(message = "Bairro é obrigatório!")
    private String bairro;

    @NotBlank(message = "Complemento é obrigatório!")
    private String complemento;

    @NotBlank(message = "Cidade é obrigatório!")
    private String cidade;

    @NotBlank(message = "Estado é obrigatório!")
    private String estado;

}
