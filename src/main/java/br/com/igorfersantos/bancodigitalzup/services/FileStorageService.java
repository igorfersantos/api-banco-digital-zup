package br.com.igorfersantos.bancodigitalzup.services;


import br.com.igorfersantos.bancodigitalzup.config.FileStorageConfig;
import br.com.igorfersantos.bancodigitalzup.exception.FileStorageException;
import br.com.igorfersantos.bancodigitalzup.exception.MyFileNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService {

    private final Path fileStorageLocation;

    @Autowired
    public FileStorageService(FileStorageConfig fileStorageConfig) {

        this.fileStorageLocation = Paths.get(fileStorageConfig.getUploadDir())
                .toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception e) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored", e);
        }
    }

    public FileStorageService () {
        fileStorageLocation = null;
    }

    public String storeFile(MultipartFile file, Long id) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if (fileName.contains("..")) {
                throw new FileStorageException("O Arquivo possui um caminho inválido! ex: (/../arquivo.jpg)" + fileName);
            }

            Path targetLocation = this.fileStorageLocation.resolve(fileName);


            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (Exception e) {
            throw new FileStorageException("Não foi possível alocar o arquivo " + fileName + ". Tente novamente mais tarde.", e);
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
