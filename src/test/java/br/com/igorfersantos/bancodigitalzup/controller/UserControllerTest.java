package br.com.igorfersantos.bancodigitalzup.controller;

import br.com.igorfersantos.bancodigitalzup.dto.UserDTO;
import br.com.igorfersantos.bancodigitalzup.repository.UserRepository;
import br.com.igorfersantos.bancodigitalzup.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    // Teste gerado antes da implementação de cpf e e-mail válido
    @Test
    void testCpfEmailException() throws Exception {
        String sDate = "05/03/1999";
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(sDate);

        UserDTO userDTO = new UserDTO(0L, "teste", "teste", "teste", date, "teste");

        // garante que já tenha um user "teste"
        try {
            userService.create(userDTO);
        } catch (Exception e) {
        }

        mockMvc.perform(post("/api/v1/users/criarUsuario")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isBadRequest());

        UserDTO userDTO2 = new UserDTO(0L,"teste", "teste", "teste1", date, "teste");

        mockMvc.perform(post("/api/v1/users/criarUsuario")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userDTO2)))
                .andExpect(status().isBadRequest());

        UserDTO userDTO3 = new UserDTO(0L,"teste", "teste", "teste", date, "teste1");

        mockMvc.perform(post("/api/v1/users/criarUsuario")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userDTO3)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testFormatoCpfValido() throws Exception {
        String sDate = "05-03-1999";
        Date date = new SimpleDateFormat("dd-MM-yyyy").parse(sDate);

        //cpf gerado em: https://www.4devs.com.br/gerador_de_cpf
        //apenas para fins de teste
        String randomCPFValido = "507.027.350-78";

        // deleta todos os usuários antes de testar
        userRepository.deleteAll();

        UserDTO userDTO = new UserDTO(0L,"teste", "teste", "igorfercontato@gmail.com", date, randomCPFValido);

        userService.create(userDTO);
    }
}
