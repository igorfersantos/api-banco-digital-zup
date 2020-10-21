package br.com.igorfersantos.bancodigitalzup.services;


import br.com.igorfersantos.bancodigitalzup.config.FileStorageConfig;
import br.com.igorfersantos.bancodigitalzup.converter.CpfFotoAdapter;
import br.com.igorfersantos.bancodigitalzup.data.dto.v1.CpfFotoDTO;
import br.com.igorfersantos.bancodigitalzup.data.model.CpfFoto;
import br.com.igorfersantos.bancodigitalzup.data.model.Cliente;
import br.com.igorfersantos.bancodigitalzup.exception.FileStorageException;
import br.com.igorfersantos.bancodigitalzup.exception.MyFileNotFoundException;
import br.com.igorfersantos.bancodigitalzup.exception.ResourceNotFoundException;
import br.com.igorfersantos.bancodigitalzup.repository.CpfFotoRepository;
import br.com.igorfersantos.bancodigitalzup.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

@Service
public class FileStorageService {

    private final Path fileStorageLocation;

    @Autowired
    CpfFotoRepository cpfFotoRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    public FileStorageService(FileStorageConfig fileStorageConfig) {

        this.fileStorageLocation = Paths.get(fileStorageConfig.getUploadDir())
                .toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception e) {
            throw new FileStorageException("Não foi possível criar o diretório dos uploads", e);
        }
    }

    public FileStorageService() {
        fileStorageLocation = null;
    }

    public CpfFotoDTO storeFile(MultipartFile file, Long id) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        if (fileName.contains("..") || fileName.isBlank()) {
            throw new FileStorageException("O Arquivo possui um caminho inválido ou em branco! ex: (/../arquivo.jpg)" + fileName);
        }

        Path targetLocation = this.fileStorageLocation.resolve(fileName);

        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("O Usuário requisitado não está cadastrado!", HttpStatus.UNPROCESSABLE_ENTITY));

        if(cliente.getEndereco() == null)
            throw new ResourceNotFoundException("O Usuário ainda não possui um endereço cadastrado!", HttpStatus.UNPROCESSABLE_ENTITY);

        Optional<CpfFoto> entity = cpfFotoRepository.findByPathId(targetLocation.toString(), id);

        if (entity.isEmpty()) {

            try {
                Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            }catch (Exception e){
                throw new FileStorageException("Não foi possível enviar o arquivo " + fileName + ". Tente novamente mais tarde.", e);
            }

            CpfFoto cpfFoto = new CpfFoto(fileName, cliente);

            return CpfFotoAdapter.toDTO(cpfFotoRepository.save(cpfFoto));
        } else {
            return CpfFotoAdapter.toDTO(entity.get());
        }
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("Arquivo não encontrado " + fileName);
            }
        } catch (Exception e) {
            throw new MyFileNotFoundException("Arquivo não encontrado " + fileName, e);
        }

    }

}
