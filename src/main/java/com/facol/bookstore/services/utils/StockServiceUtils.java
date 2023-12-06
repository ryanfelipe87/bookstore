package com.facol.bookstore.services.utils;

import com.facol.bookstore.dtos.BookDto;
import com.facol.bookstore.entities.Book;
import com.facol.bookstore.repositories.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class StockServiceUtils {

    private final BookRepository bookRepository;

    public StockServiceUtils(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public boolean verifyStock(BookDto bookDto){
        Book book = bookRepository.findByTitleAndAuthor(bookDto.getTitle(), bookDto.getAuthor());
        bookRepository.existsById(book.getId());
        return book != null && book.getAmount() > 0;
    }
}
