package br.com.igorfersantos.bancodigitalzup.controller;

import br.com.igorfersantos.bancodigitalzup.data.dto.v1.UserDTO;
import br.com.igorfersantos.bancodigitalzup.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@Api(tags = "Users Endpoint")
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    UserService userService;

    @ApiOperation(value = "cria um usuário a partir a partir do body")
    @PostMapping("/criarUsuario")
    public ResponseEntity<UserDTO> criarUsuario(@Valid @RequestBody UserDTO dto) {
        UserDTO usuario = userService.save(dto);

        WebMvcLinkBuilder controllerLinkBuilder =  WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).criarContaFisica());
        URI uri = controllerLinkBuilder.toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uri);

        return new ResponseEntity<>(usuario, headers, HttpStatus.CREATED);
    }

    @ApiOperation(value = "cria uma conta física a partir de um Usuario")
    @PostMapping("/criarContaFisica")
    public ResponseEntity<?> criarContaFisica() { //public ResponseEntity<ContaFisicaDTO> criarContaFisica(@Valid @RequestBody ContaFisicaDTO dto)
        /*UserDTO usuario = userService.save(dto);
        ResponseEntity<UserDTO> responseEntity = new ResponseEntity<>(usuario, HttpStatus.CREATED);

        WebMvcLinkBuilder controllerLinkBuilder =  WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn())*/
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("http://localhost:8080/api/v1/users/teste"));

        return new ResponseEntity<>("teste", headers, HttpStatus.CREATED);
    }
}
