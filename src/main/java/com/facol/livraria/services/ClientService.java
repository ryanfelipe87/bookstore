package com.facol.livraria.services;

import com.facol.livraria.dtos.ClientDto;

import java.util.List;

public interface ClientService {

    void create(ClientDto clientDto);

    List<ClientDto> listAll();

    ClientDto getById(Long id);

    void update(ClientDto clientDto);

    void deleteClient(ClientDto clientDto);
}
