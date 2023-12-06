package com.facol.bookstore.controller;

import com.facol.bookstore.dtos.ClientDto;
import com.facol.bookstore.repositories.ClientRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClientControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private ClientRepository clientRepository;

    ClientDto client;

    @BeforeAll
    void setUp(){
        this.client = ClientDto.builder()
                .cpf("243.922.740-47")
                .name("Neymar Jr")
                .cellPhone("(68) 99445-2451")
                .amountMoney(100.00)
                .build();
    }

    @Test
    void createClientWithCnpj(){
        ClientDto clientDto = ClientDto.builder()
                .cpf("")
                .cnpj("41.350.854/0001-85")
                .name("Mbappé")
                .cellPhone("(68) 99445-2451")
                .amountMoney(100.00)
                .cep("55606195")
                .build();

        HttpEntity<ClientDto> httpEntity = new HttpEntity<>(clientDto);

        ResponseEntity<Void> response = this.testRestTemplate
                .exchange("/client", HttpMethod.POST, httpEntity, Void.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void createClientWithCpf(){
        ClientDto clientDto = ClientDto.builder()
                .cpf("187.610.410-41")
                .cnpj("")
                .name("Bellingham")
                .cellPhone("(68) 99445-2451")
                .amountMoney(100.00)
                .cep("55606195")
                .build();

        HttpEntity<ClientDto> httpEntity = new HttpEntity<>(clientDto);

        ResponseEntity<Void> response = this.testRestTemplate
                .exchange("/client", HttpMethod.POST, httpEntity, Void.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
}
