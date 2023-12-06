package com.facol.bookstore.services;

import com.facol.bookstore.dtos.BookDto;

import java.util.List;

public interface BookService {

    BookDto createBook(BookDto bookDto);

    List<BookDto> listAllBooks();

    BookDto finBookById(Long id);

    BookDto updateBookById(Long id, BookDto bookDto);

    void deleteBook(Long id);
}
