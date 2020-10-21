package br.com.igorfersantos.bancodigitalzup.controller;

import br.com.igorfersantos.bancodigitalzup.config.EndpointUrls;
import br.com.igorfersantos.bancodigitalzup.converter.ClienteAdapter;
import br.com.igorfersantos.bancodigitalzup.data.dto.v1.ClienteDTO;
import br.com.igorfersantos.bancodigitalzup.data.model.Cliente;
import br.com.igorfersantos.bancodigitalzup.services.ClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

import static br.com.igorfersantos.bancodigitalzup.controller.EnderecoController.ENDERECO_CONTROLLER_URL;
import static br.com.igorfersantos.bancodigitalzup.controller.EnderecoController.ENDERECO_CREATE_RESOURCE;

@Api(tags = "Users Endpoint")
@RestController
@RequestMapping(EndpointUrls.CLIENTES_CONTROLLER_V1)
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    public static final String USER_CREATE_RESOURCE = "/criarCliente";

    @ApiOperation("cria um cliente a partir a partir do body")
    @PostMapping(USER_CREATE_RESOURCE)
    public ResponseEntity<ClienteDTO> criarCliente(@Valid @RequestBody ClienteDTO dto) {
        Cliente cliente = clienteService.create(dto);

        HttpHeaders headers = getHeadersWithLocationForEndereco(cliente.getId());

        return new ResponseEntity<>(ClienteAdapter.toDTO(cliente), headers, HttpStatus.CREATED);
    }

    public static HttpHeaders getHeadersWithLocationForEndereco(Long id){
        URI uri = URI.create(ENDERECO_CONTROLLER_URL + ENDERECO_CREATE_RESOURCE + '/' + id);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uri);

        return headers;
    }
}
