package br.com.igorfersantos.bancodigitalzup.converter;

import br.com.igorfersantos.bancodigitalzup.dto.EnderecoDTO;
import br.com.igorfersantos.bancodigitalzup.model.Endereco;

public class EnderecoAdapter {

    public static Endereco toEntity(EnderecoDTO dto){
        return new Endereco(dto.getCep(),dto.getBairro(), dto.getComplemento(), dto.getCidade(), dto.getEstado());
    }

    public static EnderecoDTO toDTO(Endereco endereco){
        return new EnderecoDTO(endereco.getCep(),endereco.getBairro(), endereco.getComplemento(), endereco.getCidade(), endereco.getEstado());
    }
}
