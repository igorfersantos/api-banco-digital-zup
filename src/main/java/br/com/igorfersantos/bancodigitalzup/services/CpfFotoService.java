package br.com.igorfersantos.bancodigitalzup.services;

import br.com.igorfersantos.bancodigitalzup.converter.CpfFotoAdapter;
import br.com.igorfersantos.bancodigitalzup.data.dto.v1.CpfFotoDTO;
import br.com.igorfersantos.bancodigitalzup.data.model.CpfFoto;
import br.com.igorfersantos.bancodigitalzup.repository.CpfFotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CpfFotoService {

    @Autowired
    CpfFotoRepository cpfFotoRepository;

    public CpfFotoDTO create(CpfFotoDTO cpfFotoDTO){
        CpfFoto entity = cpfFotoRepository.save(CpfFotoAdapter.toEntity(cpfFotoDTO));
        return CpfFotoAdapter.toDTO(entity);
    }
 }
