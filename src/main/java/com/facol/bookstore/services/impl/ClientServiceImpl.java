package com.facol.bookstore.services.impl;

import com.facol.bookstore.dtos.BookDto;
import com.facol.bookstore.dtos.ClientDto;
import com.facol.bookstore.entities.Client;
import com.facol.bookstore.exceptions.GenericNotFoundException;
import com.facol.bookstore.patterns.factory.PersonFactory;
import com.facol.bookstore.patterns.mapper.ClientDtoToClientMapper;
import com.facol.bookstore.patterns.mapper.ClientToClientDtoMapper;
import com.facol.bookstore.patterns.mapper.ClientWithCnpjMapper;
import com.facol.bookstore.patterns.mapper.ClientWithCpfMapper;
import com.facol.bookstore.patterns.singleton.LoggerSingleton;
import com.facol.bookstore.repositories.ClientRepository;
import com.facol.bookstore.services.ClientService;
import com.facol.bookstore.services.utils.PaymentServiceUtils;
import com.facol.bookstore.services.utils.SendServiceUtils;
import com.facol.bookstore.services.utils.StockServiceUtils;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    private Logger logger = LoggerSingleton.getLogger();
    private ClientRepository clientRepository;
    private ClientWithCnpjMapper clientCnpj;
    private ClientWithCpfMapper clientCpf;
    private ClientToClientDtoMapper mapperClientDto;
    private ClientDtoToClientMapper mapperClient;
    private StockServiceUtils stockServiceUtils;
    private SendServiceUtils sendServiceUtils;
    private PaymentServiceUtils paymentServiceUtils;


    @Override
    public ClientDto create(ClientDto clientDto) {
        try {
            PersonFactory factory = new PersonFactory(clientCnpj, clientCpf);
            Client client = factory.getPerson(clientDto);
            clientRepository.save(client);

            return clientDto;
        }catch (Exception e){
            logger.warning("Create a new client error.");
            throw new GenericNotFoundException("Create a new client error.");
        }
    }
    @Transactional
    @Override
    public List<ClientDto> listAll() {
        List<Client> clientList = clientRepository.findAll();
        List<ClientDto> clientDtoList = new ArrayList<>();

        if (!clientList.isEmpty()) {
            for (Client client : clientList) {
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

    @Override
    public boolean makePurchase(ClientDto clientDto, BookDto bookDto) {
        if (stockServiceUtils.verifyStock(bookDto)) {
            if (paymentServiceUtils.verifyPayment(clientDto, bookDto)) {
                sendServiceUtils.sendBook(clientDto, bookDto);
                return true;
            } else {
                logger.info("Payment not confirmed. The purchase will not be completed.");
                return false;
            }
        } else {
            logger.info("The book is not available. The purchase will not be completed.");
            return false;
        }
    }

    private Client getClient(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new GenericNotFoundException("User not found"));
    }
}
