package br.com.igorfersantos.bancodigitalzup.controller;

import br.com.igorfersantos.bancodigitalzup.data.dto.v1.ClienteDTO;
import br.com.igorfersantos.bancodigitalzup.repository.ClienteRepository;
import br.com.igorfersantos.bancodigitalzup.services.ClienteService;
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
class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteRepository clienteRepository;

    // Teste gerado antes da implementação de cpf e e-mail válido
    @Test
    void testCpfEmailException() throws Exception {
        String sDate = "05/03/1999";
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(sDate);

        ClienteDTO clienteDTO = new ClienteDTO( "teste", "teste", "teste", date, "teste");

        // garante que já tenha um user "teste"
        try {
            clienteService.create(clienteDTO);
        } catch (Exception e) {
        }

        mockMvc.perform(post("/api/v1/users/criarUsuario")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(clienteDTO)))
                .andExpect(status().isBadRequest());

        ClienteDTO clienteDTO2 = new ClienteDTO("teste", "teste", "teste1", date, "teste");

        mockMvc.perform(post("/api/v1/users/criarUsuario")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(clienteDTO2)))
                .andExpect(status().isBadRequest());

        ClienteDTO clienteDTO3 = new ClienteDTO("teste", "teste", "teste", date, "teste1");

        mockMvc.perform(post("/api/v1/users/criarUsuario")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(clienteDTO3)))
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
        clienteRepository.deleteAll();

        ClienteDTO clienteDTO = new ClienteDTO("teste", "teste", "igorfercontato@gmail.com", date, randomCPFValido);

        clienteService.create(clienteDTO);
    }
}
