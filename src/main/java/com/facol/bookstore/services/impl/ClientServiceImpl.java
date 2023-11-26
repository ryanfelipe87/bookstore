package com.facol.bookstore.services.impl;

import com.facol.bookstore.dtos.ClientDto;
import com.facol.bookstore.services.ClientService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    @Override
    public void create(ClientDto clientDto) {

    }

    @Override
    public List<ClientDto> listAll() {
        return null;
    }

    @Override
    public ClientDto getById(Long id) {
        return null;
    }

    @Override
    public void update(ClientDto clientDto) {

    }

    @Override
    public void deleteClient(ClientDto clientDto) {

    }
}
