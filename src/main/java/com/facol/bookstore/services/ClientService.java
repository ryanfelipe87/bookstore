package com.facol.bookstore.services;

import com.facol.bookstore.dtos.BookDto;
import com.facol.bookstore.dtos.ClientDto;

import java.util.List;

public interface ClientService {

    void create(ClientDto clientDto);

    List<ClientDto> listAll();

    ClientDto getById(Long id);

    void update(ClientDto clientDto);

    void deleteClient(Long id);

    boolean makePurchase(ClientDto clientDto, BookDto bookDto);
}
