package com.facol.bookstore.controllers;

import com.facol.bookstore.dtos.BookDto;
import com.facol.bookstore.dtos.ClientDto;
import com.facol.bookstore.patterns.singleton.LoggerSingleton;
import com.facol.bookstore.services.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/client")
@Tag(name = "Client")
public class ClientController {

    private Logger logger = LoggerSingleton.getLogger();
    private final ClientService service;

    public ClientController(ClientService service, Logger logger){
        this.service = service;
        this.logger = logger;
    }

    @PostMapping
    @Operation(
            summary = "Create a new client",
            description = "Controller for a Client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful request."),
            @ApiResponse(responseCode = "400", description = "Invalid request due to pre-defined validations."),
            @ApiResponse(responseCode = "404", description = "Resource not found."),
            @ApiResponse(responseCode = "500", description = "Unidentified internal error on the server."),
    })
    public ResponseEntity<Void> create(@RequestBody ClientDto clientDto){
        logger.info("Creating a new user...");
        service.create(clientDto);
        logger.info("New user created.");
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @PostMapping(path = "/purchase")
    @Operation(
            summary = "Make a new purchase",
            description = "Controller for a Client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful request."),
            @ApiResponse(responseCode = "400", description = "Invalid request due to pre-defined validations."),
            @ApiResponse(responseCode = "404", description = "Resource not found."),
            @ApiResponse(responseCode = "500", description = "Unidentified internal error on the server."),
    })
    public ResponseEntity<String> makePurchase(@RequestBody BookDto bookDto){
        ClientDto clientDto = getCustomerInfo();

        boolean purchaseSuccessfully = service.makePurchase(clientDto, bookDto);

        if(purchaseSuccessfully){
            return ResponseEntity.ok("Purchase made successfully.");
        }else{
            return ResponseEntity.badRequest().body("Purchase failed. Check details and try again.");
        }
    }

    @GetMapping
    @Operation(
            summary = "List all Clients",
            description = "Controller for a Client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful request."),
            @ApiResponse(responseCode = "400", description = "Invalid request due to pre-defined validations."),
            @ApiResponse(responseCode = "404", description = "Resource not found."),
            @ApiResponse(responseCode = "500", description = "Unidentified internal error on the server."),
    })
    public ResponseEntity<List<ClientDto>> listAll(){
        logger.info("Listing all users...");
        List<ClientDto> clients = service.listAll();
        logger.info("Complete listing.");
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    @Operation(
            summary = "Find Client by id",
            description = "Controller for a Client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful request."),
            @ApiResponse(responseCode = "400", description = "Invalid request due to pre-defined validations."),
            @ApiResponse(responseCode = "404", description = "Resource not found."),
            @ApiResponse(responseCode = "500", description = "Unidentified internal error on the server."),
    })
    public ResponseEntity<ClientDto> getById(@PathVariable Long id){
        logger.info("Listing users by id...");
        ClientDto clientDto = service.getById(id);
        logger.info("List of users by id complete.");
        return new ResponseEntity<>(clientDto, HttpStatus.OK);
    }

    @PutMapping
    @Operation(
            summary = "Update Client",
            description = "Controller for a Client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful request."),
            @ApiResponse(responseCode = "400", description = "Invalid request due to pre-defined validations."),
            @ApiResponse(responseCode = "404", description = "Resource not found."),
            @ApiResponse(responseCode = "500", description = "Unidentified internal error on the server."),
    })
    public ResponseEntity<Void> update(@RequestBody ClientDto clientDto){
        logger.info("Updating a new user...");
        service.update(clientDto);
        logger.info("User has been updated.");
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    @Operation(
            summary = "Delete Client",
            description = "Controller for a Client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful request."),
            @ApiResponse(responseCode = "400", description = "Invalid request due to pre-defined validations."),
            @ApiResponse(responseCode = "404", description = "Resource not found."),
            @ApiResponse(responseCode = "500", description = "Unidentified internal error on the server."),
    })
    public ResponseEntity<Void> delete(@PathVariable Long id){
        logger.info("Removing user...");
        service.deleteClient(id);
        logger.info("User removed successfully.");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private ClientDto getCustomerInfo(){
        ClientDto clientDto = new ClientDto();
        clientDto.getId();
        clientDto.getName();
        clientDto.getCpf();
        clientDto.getCnpj();
        clientDto.getAmountMoney();
        clientDto.getCellPhone();
        clientDto.getCep();
        clientDto.getAddress();

        return clientDto;
    }
}
