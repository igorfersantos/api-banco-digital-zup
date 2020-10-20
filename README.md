# Banco Digital Zup

### Status
O Projeto atualmente se encontra na terceira parte das sete propostas do desafio.

---

As tecnologias utilizadas até o momento são:

- Java 11
- Spring Data JPA para consultas no banco
- Spring Web para criação dos serviços REST 
- Spring MockMvc para testar os serviços
- Spring Actuator para monitoramento da API
- Spring HATEOAS habilitado porém está sendo utilizado apenas para obtenção dos recursos no momento
- Hibernate Validator para validação dos dados providos nos endpoints
- Flyway Migration para automatizar a criação das tabelas e dados
- Swagger e Swagger UI para auto-documentação da API
- Lombok para redução de código escrito
- Caelum Stella e Commons Validator para validação de dados como CPF e E-mail

Tentei ao máximo aplicar a metodologia dos 12 fatores, usando de variáveis de ambiente e separação do código por lógica, serviço, etc.


# Inicializando o projeto pela linha de comando/fora da IDE (sem Docker)


1.	Navegue até a pasta banco-digital-zup/App
2.	Execute o flyway migration para inicilizar o banco de dados
    ***OBS: certifique-se que a base "banco_digital_zup" já foi criada e está vazia, caso contrário o flyway não irá executar as migrações***


```
./mvwn flyway:migrate
```

3.	Dê start na API


```
./mvnw clean spring-boot:run
```


# Endpoint de início


A Documentação para os endpoints e o body esperado pode ser vista em

>http://localhost:8080/swagger-ui.html


_Primeiro endpoint: Criação de usuário com nome, sobrenomecpf, data de nascimento e e-mail_
_(a classe DTO pode ser vista na documentação)_

>https://localhost:8080/api/v1/users/criarUsuario

