package com.facol.bookstore.services.impl;

import com.facol.bookstore.dtos.BookDto;
import com.facol.bookstore.entities.Book;
import com.facol.bookstore.exceptions.GenericNotFoundException;
import com.facol.bookstore.repositories.BookRepository;
import com.facol.bookstore.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public BookDto createBook(BookDto bookDto) {
        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setAmount(bookDto.getAmount());

        book = bookRepository.save(book);

        return convertToDto(book);
    }

    @Override
    public List<BookDto> listAllBooks() {
        List<Book> book = bookRepository.findAll();
        return book.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public BookDto finBookById(Long id) {
        Book book = bookRepository.findById(id).orElse(null);
        if(book != null){
            return convertToDto(book);
        }
        throw new GenericNotFoundException("Book with id " + id + " not found.");
    }

    @Override
    public BookDto updateBookById(Long id, BookDto bookDto) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if(bookOptional.isPresent()){
            Book book = bookOptional.get();
            book.setTitle(bookDto.getTitle());
            book.setAuthor(bookDto.getAuthor());
            book.setAmount(bookDto.getAmount());

            book = bookRepository.save(book);

            return convertToDto(book);
        }
        throw new GenericNotFoundException("Update not realized, the ID " + id + " not found.");
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    private BookDto convertToDto(Book book){
        BookDto bookDto = new BookDto();
        bookDto.setTitle(book.getTitle());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setAmount(book.getAmount());
        return bookDto;
    }
}
