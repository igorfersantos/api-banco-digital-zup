package br.com.igorfersantos.bancodigitalzup.controller;

import br.com.igorfersantos.bancodigitalzup.data.dto.v1.UploadFileResponseDTO;
import br.com.igorfersantos.bancodigitalzup.services.FileStorageService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static br.com.igorfersantos.bancodigitalzup.Application.BASE_URL;

@Api(tags = "FileEndpoint")
@RestController
@RequestMapping("/api/file/v1")
public class FileController {
	
	private static final Logger logger = LoggerFactory.getLogger(FileController.class);

	public static final String FILE_CONTROLLER_URL = BASE_URL + WebMvcLinkBuilder.linkTo(EnderecoController.class).toString();
	public static final String FILE_CREATE_RESOURCE = "/uploadFile";

	@Autowired
	FileStorageService fileStorageService;
	
	@PostMapping("/uploadFile/{id}")
	public UploadFileResponseDTO uploadFile(@RequestParam("file") MultipartFile file, @PathVariable("id") Long id) {
		String fileName = fileStorageService.storeFile(file, id);
		
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/api/file/v1/downloadFile/")
				.path(fileName)
				.toUriString();
		
		return new UploadFileResponseDTO(fileName, fileDownloadUri, file.getContentType(), file.getSize());
	}
	
	/*@PostMapping("/uploadMultipleFiles")
	public List<UploadFileResponseDTO> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
		return Arrays.asList(files)
				.stream()
				.map(file -> uploadFile(file))
				.collect(Collectors.toList());
	}
	
	@GetMapping("/downloadFile/{fileName:.+}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
		
		Resource resource = fileStorageService.loadFileAsResource(fileName);
		
		String contentType = null;
		
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (Exception e) {
			logger.info("Could not determine file type!");
		}
		
		if (contentType == null) {
			contentType = "application/octet-stream";
		}
		
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
				
	}*/

}
