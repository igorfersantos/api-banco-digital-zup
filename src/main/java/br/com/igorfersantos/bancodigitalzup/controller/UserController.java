package br.com.igorfersantos.bancodigitalzup.controller;

import br.com.igorfersantos.bancodigitalzup.dto.UserDTO;
import br.com.igorfersantos.bancodigitalzup.services.UserService;
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
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    UserService userService;

    public static final String USER_CREATE_RESOURCE = "/criarUsuario";

    @ApiOperation("cria um usuário a partir a partir do body")
    @PostMapping(USER_CREATE_RESOURCE)
    public ResponseEntity<UserDTO> criarUsuario(@Valid @RequestBody UserDTO dto) {
        UserDTO usuario = userService.create(dto);

        URI uri = URI.create(ENDERECO_CONTROLLER_URL + ENDERECO_CREATE_RESOURCE + '/' + usuario.getId());
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uri);

        return new ResponseEntity<>(usuario, headers, HttpStatus.CREATED);
    }
}
