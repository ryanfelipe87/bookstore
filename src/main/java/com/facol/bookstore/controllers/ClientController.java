package com.facol.bookstore.controllers;

import com.facol.bookstore.dtos.BookDto;
import com.facol.bookstore.dtos.ClientDto;
import com.facol.bookstore.patterns.singleton.LoggerSingleton;
import com.facol.bookstore.services.impl.ClientServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@Controller
@Tag(name = "Client")
public class ClientController {

    private Logger logger = LoggerSingleton.getLogger();
    private final ClientServiceImpl service;

    public ClientController(ClientServiceImpl service, Logger logger){
        this.service = service;
        this.logger = logger;
    }

    @PostMapping("/client")
    @Operation(
            summary = "Create a new client",
            description = "Controller for a Client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful request."),
            @ApiResponse(responseCode = "400", description = "Invalid request due to pre-defined validations."),
            @ApiResponse(responseCode = "404", description = "Resource not found."),
            @ApiResponse(responseCode = "500", description = "Unidentified internal error on the server."),
    })
    public String create(@RequestBody ClientDto clientDto, Model model){
        logger.info("Creating a new user...");
        service.create(clientDto);
        logger.info("New user created.");
        new ResponseEntity<Void>(HttpStatus.CREATED);
        model.addAttribute("client", clientDto);
        return "client";
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
    public String makePurchase(@RequestBody BookDto bookDto, Model model){
        ClientDto clientDto = getCustomerInfo();

        boolean purchaseSuccessfully = service.makePurchase(clientDto, bookDto);

        if(purchaseSuccessfully){
            logger.info("Purchase made successfully.");
        }else{
            logger.info("Purchase failed. Check details and try again.");
        }
        model.addAttribute("purchase", bookDto);
        return "/purchase";
    }

    @GetMapping("/client")
    @Operation(
            summary = "List all Clients",
            description = "Controller for a Client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful request."),
            @ApiResponse(responseCode = "400", description = "Invalid request due to pre-defined validations."),
            @ApiResponse(responseCode = "404", description = "Resource not found."),
            @ApiResponse(responseCode = "500", description = "Unidentified internal error on the server."),
    })
    public String listAll(Model model){
        logger.info("Listing all users...");
        List<ClientDto> clients = service.listAll();
        logger.info("Complete listing.");
        new ResponseEntity<>(clients, HttpStatus.OK);
        model.addAttribute("client", clients);
        return "/client";
    }

    @GetMapping(path = "/client/{id}")
    @Operation(
            summary = "Find Client by id",
            description = "Controller for a Client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful request."),
            @ApiResponse(responseCode = "400", description = "Invalid request due to pre-defined validations."),
            @ApiResponse(responseCode = "404", description = "Resource not found."),
            @ApiResponse(responseCode = "500", description = "Unidentified internal error on the server."),
    })
    public String getById(@PathVariable Long id, Model model){
        logger.info("Listing users by id...");
        ClientDto clientDto = service.getById(id);
        logger.info("List of users by id complete.");
        new ResponseEntity<>(clientDto, HttpStatus.OK);
        model.addAttribute("client", clientDto);
        return "/client";
    }

    @PutMapping("/client/{id}")
    @Operation(
            summary = "Update Client",
            description = "Controller for a Client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful request."),
            @ApiResponse(responseCode = "400", description = "Invalid request due to pre-defined validations."),
            @ApiResponse(responseCode = "404", description = "Resource not found."),
            @ApiResponse(responseCode = "500", description = "Unidentified internal error on the server."),
    })
    public String update(@RequestBody ClientDto clientDto, Model model){
        logger.info("Updating a new user...");
        service.update(clientDto);
        logger.info("User has been updated.");
        new ResponseEntity<Void>(HttpStatus.CREATED);
        model.addAttribute("client", clientDto);
        return "/client";
    }

    @DeleteMapping(path = "/client/{id}")
    @Operation(
            summary = "Delete Client",
            description = "Controller for a Client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful request."),
            @ApiResponse(responseCode = "400", description = "Invalid request due to pre-defined validations."),
            @ApiResponse(responseCode = "404", description = "Resource not found."),
            @ApiResponse(responseCode = "500", description = "Unidentified internal error on the server."),
    })
    public String delete(@PathVariable Long id, Model model){
        logger.info("Removing user...");
        service.deleteClient(id);
        logger.info("User removed successfully.");
        new ResponseEntity<>(HttpStatus.NO_CONTENT);
        model.addAttribute("client");
        return "/client";
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
