package com.facol.bookstore.controllers;

import com.facol.bookstore.dtos.ClientDto;
import com.facol.bookstore.patterns.singleton.LoggerSingleton;
import com.facol.bookstore.services.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/client")
public class ClientController {

    private Logger logger = LoggerSingleton.getLogger();
    private final ClientService service;

    public ClientController(ClientService service, Logger logger){
        this.service = service;
        this.logger = logger;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody ClientDto clientDto){
        logger.info("Creating a new user...");
        service.create(clientDto);
        logger.info("New user created.");
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ClientDto>> listAll(){
        logger.info("Listing all users...");
        List<ClientDto> clients = service.listAll();
        logger.info("Complete listing.");
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ClientDto> getById(@PathVariable Long id){
        logger.info("Listing users by id...");
        ClientDto clientDto = service.getById(id);
        logger.info("List of users by id complete.");
        return new ResponseEntity<>(clientDto, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody ClientDto clientDto){
        logger.info("Updating a new user...");
        service.update(clientDto);
        logger.info("User has been updated.");
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        logger.info("Removing user...");
        service.deleteClient(id);
        logger.info("User removed successfully.");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
