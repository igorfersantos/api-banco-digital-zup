package br.com.igorfersantos.bancodigitalzup.controller;

import br.com.igorfersantos.bancodigitalzup.config.EndpointUrls;
import br.com.igorfersantos.bancodigitalzup.data.dto.v1.EnderecoDTO;
import br.com.igorfersantos.bancodigitalzup.services.EnderecoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

import static br.com.igorfersantos.bancodigitalzup.Application.BASE_URL;


@Api(tags = "Address Endpoint")
@RestController
@RequestMapping(EndpointUrls.ENDERECOS_CONTROLLER_V1)
public class EnderecoController {

    public static final String ENDERECO_CONTROLLER_URL= BASE_URL + WebMvcLinkBuilder.linkTo(EnderecoController.class).toString();
    public static final String ENDERECO_CREATE_RESOURCE = "/criarEndereco";

    @Autowired
    EnderecoService enderecoService;

    @ApiOperation("Cria um endereço a partir a partir do body")
    @PostMapping("/criarEndereco/{id}")
    public ResponseEntity<EnderecoDTO> criarEndereco(@Valid @RequestBody EnderecoDTO dto, @PathVariable("id") Long id) {
        EnderecoDTO enderecoDTO = enderecoService.create(dto, id);

        URI uri = URI.create(FileController.FILE_CONTROLLER_URL + FileController.FILE_CREATE_RESOURCE + "/" + id);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uri);

        return new ResponseEntity<>(enderecoDTO, headers, HttpStatus.CREATED);
    }
}
