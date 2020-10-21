package br.com.igorfersantos.bancodigitalzup.services;


import br.com.igorfersantos.bancodigitalzup.config.FileStorageConfig;
import br.com.igorfersantos.bancodigitalzup.converter.CpfFotoAdapter;
import br.com.igorfersantos.bancodigitalzup.data.dto.v1.CpfFotoDTO;
import br.com.igorfersantos.bancodigitalzup.data.model.Cliente;
import br.com.igorfersantos.bancodigitalzup.data.model.CpfFoto;
import br.com.igorfersantos.bancodigitalzup.exception.FileStorageException;
import br.com.igorfersantos.bancodigitalzup.exception.MyFileNotFoundException;
import br.com.igorfersantos.bancodigitalzup.exception.ResourceNotFoundException;
import br.com.igorfersantos.bancodigitalzup.repository.ClienteRepository;
import br.com.igorfersantos.bancodigitalzup.repository.CpfFotoRepository;
import br.com.igorfersantos.bancodigitalzup.repository.EnderecoRepository;
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
    ClienteRepository clienteRepository;

    @Autowired
    CpfFotoRepository cpfFotoRepository;

    @Autowired
    public FileStorageService(FileStorageConfig fileStorageConfig) {

        this.fileStorageLocation = Paths.get(fileStorageConfig.getUploadDir())
                .toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception e) {
            throw new FileStorageException("Não foi possível criar o diretório onde os arquivos serão salvos", e);
        }
    }

    public FileStorageService() {
        fileStorageLocation = null;
    }

    public CpfFotoDTO storeFile(MultipartFile file, Long id) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        if (fileName.contains("..") || fileName.isBlank()) {
            throw new FileStorageException("O Arquivo possui um caminho inválido! ex: (/../arquivo.jpg) Caminho: " + fileName);
        }

        Path targetLocation = this.fileStorageLocation.resolve(fileName);

        Cliente clienteResult = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não cadastrado!", HttpStatus.UNPROCESSABLE_ENTITY));

        if(clienteResult.getEndereco() == null)
            throw new ResourceNotFoundException("Usuário não possui endereço cadastrado!", HttpStatus.UNPROCESSABLE_ENTITY);

        Optional<CpfFoto> cpfFotoResult = cpfFotoRepository.findByPathId(targetLocation.toString(), clienteResult.getId());

        CpfFoto cpfFotoEntity = null;

        if(cpfFotoResult.isEmpty()) {
            cpfFotoEntity = new CpfFoto(targetLocation.toString(), clienteResult);
            cpfFotoEntity = cpfFotoRepository.save(cpfFotoEntity);
        }else {
            return CpfFotoAdapter.toDTO(cpfFotoResult.get());
        }

        try {
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            throw new FileStorageException("Não foi possível alocar o arquivo " + fileName + ". Tente novamente mais tarde.", e);
        }

        return CpfFotoAdapter.toDTO(cpfFotoEntity);
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
