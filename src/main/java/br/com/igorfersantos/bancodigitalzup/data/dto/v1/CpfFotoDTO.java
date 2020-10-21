package br.com.igorfersantos.bancodigitalzup.data.dto.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class CpfFotoDTO implements Serializable {

    private static final long serialVersionUID = 8683332930676984285L;

    private String foto;

    @JsonProperty("user")
    private ClienteDTO clienteDTO = null;
}
