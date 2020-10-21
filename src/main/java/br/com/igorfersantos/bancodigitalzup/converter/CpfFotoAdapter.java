package br.com.igorfersantos.bancodigitalzup.converter;

import br.com.igorfersantos.bancodigitalzup.data.dto.v1.CpfFotoDTO;
import br.com.igorfersantos.bancodigitalzup.data.model.CpfFoto;

public class CpfFotoAdapter {

    public static CpfFoto toEntity(CpfFotoDTO dto){
        return new CpfFoto(dto.getFoto(), ClienteAdapter.toEntity(dto.getClienteDTO()));
    }

    public static CpfFotoDTO toDTO(CpfFoto cpfFoto){
        return new CpfFotoDTO(cpfFoto.getFoto(), ClienteAdapter.toDTO(cpfFoto.getCliente()));
    }
}
