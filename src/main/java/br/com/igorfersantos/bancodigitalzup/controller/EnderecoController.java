package br.com.igorfersantos.bancodigitalzup.controller;

import br.com.igorfersantos.bancodigitalzup.dto.EnderecoDTO;
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

import static br.com.igorfersantos.bancodigitalzup.config.HostConfig.BASE_URL;

@Api(tags = "Address Endpoint")
@RestController
@RequestMapping("/api/v1/address")
public class EnderecoController {

    @Autowired
    EnderecoService enderecoService;

    public static String ENDERECO_CONTROLLER_URL = BASE_URL + WebMvcLinkBuilder.linkTo(EnderecoController.class).toString();
    public static final String ENDERECO_CREATE_RESOURCE = "/criarEndereco";

    @ApiOperation("cria um endereço a partir a partir do body")
    @PostMapping(ENDERECO_CREATE_RESOURCE + "/{id}")
    public ResponseEntity<EnderecoDTO> criarEndereco(@Valid @RequestBody EnderecoDTO dto, @PathVariable("id") Long id) {
        EnderecoDTO enderecoDTO = enderecoService.save(dto, id);

        URI uri = URI.create(ENDERECO_CONTROLLER_URL + ENDERECO_CREATE_RESOURCE);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uri);

        return new ResponseEntity<>(enderecoDTO, headers, HttpStatus.CREATED);
    }
}
