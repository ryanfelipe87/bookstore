package com.facol.bookstore.services.impl;

import com.facol.bookstore.dtos.ClientDto;
import com.facol.bookstore.entities.Client;
import com.facol.bookstore.exceptions.GenericNotFoundException;
import com.facol.bookstore.patterns.factory.PersonFactory;
import com.facol.bookstore.patterns.mapper.ClientDtoToClientMapper;
import com.facol.bookstore.patterns.mapper.ClientToClientDtoMapper;
import com.facol.bookstore.patterns.mapper.ClientWithCnpjMapper;
import com.facol.bookstore.patterns.mapper.ClientWithCpfMapper;
import com.facol.bookstore.repositories.ClientRepository;
import com.facol.bookstore.services.ClientService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;
    private ClientWithCnpjMapper clientCnpj;
    private ClientWithCpfMapper clientCpf;
    private ClientToClientDtoMapper mapperClientDto;
    private ClientDtoToClientMapper mapperClient;

    @Override
    public void create(ClientDto clientDto) {
        PersonFactory factory = new PersonFactory(clientCnpj, clientCpf);
        Client client = factory.getPerson(clientDto);
        clientRepository.save(client);
    }

    @Transactional
    @Override
    public List<ClientDto> listAll() {
        List<Client> clientList = clientRepository.findAll();
        List<ClientDto> clientDtoList = new ArrayList<>();

        if(!clientList.isEmpty()){
            for(Client client : clientList){
                ClientDto dto = mapperClientDto.map(client, new ClientDto());
                clientDtoList.add(dto);
            }
        }
        return clientDtoList;
    }

    @Override
    public ClientDto getById(Long id) {
        Client client = getClient(id);
        return mapperClientDto.map(client, new ClientDto());
    }

    @Override
    public void update(ClientDto clientDto) {
        Client client = getClient(clientDto.getId());
        Client client1 = mapperClient.map(clientDto, client);
        clientRepository.save(client1);
    }

    @Override
    public void deleteClient(Long id) {
        Client client = getClient(id);
        clientRepository.delete(client);
    }

    private Client getClient(Long id){
        return clientRepository.findById(id)
                .orElseThrow(() -> new GenericNotFoundException("User not found"));
    }
}
