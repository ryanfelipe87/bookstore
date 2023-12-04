package com.facol.bookstore.repository;

import com.facol.bookstore.dtos.ClientDto;
import com.facol.bookstore.entities.Client;
import com.facol.bookstore.repositories.ClientRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class ClientRepositoryTest {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("Should list all client from DB")
    void findAllClientFromDB(){
        ClientDto clientDto = ClientDto.builder()
                .cpf("243.922.740-47")
                .name("Cristiano Ronaldo")
                .cellPhone("(68) 99445-2451")
                .build();
        this.createUser(clientDto);

        List<Client> result = this.clientRepository.findAll();

        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getName()).isEqualTo(clientDto.getName());
    }

    @Test
    @DisplayName("Should list client by cpf")
    void findClientByCpf(){
        ClientDto clientDto = ClientDto.builder()
                .cpf("243.922.740-47")
                .name("Lionel Messi")
                .cellPhone("(68) 99445-2451")
                .build();
        this.createUser(clientDto);

        Client result = this.clientRepository.searchClientForCpf(clientDto.getCpf());

        assertThat(result.getName()).isEqualTo(clientDto.getName());
    }

    private void createUser(ClientDto data){
        Client client = new Client();
        client.setCpf(data.getCpf());
        client.setName(data.getName());
        client.setCellPhone(data.getCellPhone());
        this.entityManager.persist(client);
    }

}
