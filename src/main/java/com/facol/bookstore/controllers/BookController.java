package com.facol.bookstore.controllers;

import com.facol.bookstore.dtos.BookDto;
import com.facol.bookstore.patterns.singleton.LoggerSingleton;
import com.facol.bookstore.services.impl.BookServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@Controller
@Tag(name = "Book")
public class BookController {

    private Logger logger = LoggerSingleton.getLogger();
    private final BookServiceImpl service;

    public BookController(BookServiceImpl service, Logger logger){
        this.service = service;
        this.logger = logger;
    }

    @PostMapping("/books")
    @Operation(
            summary = "Create a new book",
            description = "Controller for a Book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful request."),
            @ApiResponse(responseCode = "400", description = "Invalid request due to pre-defined validations."),
            @ApiResponse(responseCode = "404", description = "Resource not found."),
            @ApiResponse(responseCode = "500", description = "Unidentified internal error on the server."),
    })
    public String saveBook(@RequestBody BookDto bookDto, Model model){
        service.createBook(bookDto);
        new ResponseEntity<Void>(HttpStatusCode.valueOf(200));
        model.addAttribute("books", bookDto);
        return "/books";
    }

    @GetMapping("/books")
    @Operation(
            summary = "List all books",
            description = "Controller for a Book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful request."),
            @ApiResponse(responseCode = "400", description = "Invalid request due to pre-defined validations."),
            @ApiResponse(responseCode = "404", description = "Resource not found."),
            @ApiResponse(responseCode = "500", description = "Unidentified internal error on the server."),
    })
    public String listAllBooks(Model model){
        service.listAllBooks();
        model.addAttribute("books");
        return "/books";
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Find book by id",
            description = "Controller for a Book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful request."),
            @ApiResponse(responseCode = "400", description = "Invalid request due to pre-defined validations."),
            @ApiResponse(responseCode = "404", description = "Resource not found."),
            @ApiResponse(responseCode = "500", description = "Unidentified internal error on the server."),
    })
    public String findBookById(@PathVariable Long id, Model model){
        service.finBookById(id);
        model.addAttribute("books");
        return "/books";
    }

    @PutMapping("/books/{id}")
    @Operation(
            summary = "Update book by id",
            description = "Controller for a Book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful request."),
            @ApiResponse(responseCode = "400", description = "Invalid request due to pre-defined validations."),
            @ApiResponse(responseCode = "404", description = "Resource not found."),
            @ApiResponse(responseCode = "500", description = "Unidentified internal error on the server."),
    })
    public String updateBook(@PathVariable Long id, @RequestBody BookDto bookDto, Model model){
        service.updateBookById(id, bookDto);
        model.addAttribute("books", bookDto);
        return "/books";
    }

    @DeleteMapping("/books/{id}")
    @Operation(
            summary = "Delete book by id",
            description = "Controller for a Book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful request."),
            @ApiResponse(responseCode = "400", description = "Invalid request due to pre-defined validations."),
            @ApiResponse(responseCode = "404", description = "Resource not found."),
            @ApiResponse(responseCode = "500", description = "Unidentified internal error on the server."),
    })
    public String deleteBook(@PathVariable Long id, Model model){
        service.deleteBook(id);
        model.addAttribute("books");
        return "/books";
    }
}
