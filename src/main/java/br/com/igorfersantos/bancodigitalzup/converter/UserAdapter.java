package br.com.igorfersantos.bancodigitalzup.converter;

import br.com.igorfersantos.bancodigitalzup.data.dto.v1.ClienteDTO;
import br.com.igorfersantos.bancodigitalzup.data.model.Cliente;

import java.time.format.DateTimeFormatter;

public class UserAdapter {

    private static final DateTimeFormatter dateFormatter
            = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static Cliente toEntity(ClienteDTO dto){
        return new Cliente(dto.getNome(), dto.getSobrenome(), dto.getEmail(), dto.getDataNascimento(), dto.getCpf());
    }

    public static ClienteDTO toDTO(Cliente cliente){
        return new ClienteDTO(cliente.getNome(), cliente.getSobrenome(), cliente.getEmail(), cliente.getDataNascimento(), cliente.getCpf());
    }
}
