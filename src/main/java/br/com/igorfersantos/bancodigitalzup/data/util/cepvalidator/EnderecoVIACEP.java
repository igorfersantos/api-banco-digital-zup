package br.com.igorfersantos.bancodigitalzup.util.cepvalidator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true) // Caso a estrutura mude com o tempo
public class EnderecoVIACEP implements Serializable {

    private static final long serialVersionUID = -8886283677471403617L;

    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
    private String ibge;
    private String gia;
    private String ddd;
    private String siafi;
}
