package com.facol.bookstore.services.utils;

import com.facol.bookstore.dtos.BookDto;
import com.facol.bookstore.entities.Book;
import com.facol.bookstore.exceptions.GenericNotFoundException;
import com.facol.bookstore.patterns.singleton.LoggerSingleton;
import com.facol.bookstore.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class StockServiceUtils {

    private final BookRepository bookRepository;
    private Logger logger = LoggerSingleton.getLogger();

    public StockServiceUtils(BookRepository bookRepository, Logger logger){
        this.bookRepository = bookRepository;
        this.logger = logger;
    }

    public boolean verifyStock(BookDto bookDto){
        Book book = bookRepository.findByTitleAndAuthor(bookDto.getTitle(), bookDto.getAuthor());
        bookRepository.existsById(book.getId());
        return book != null && book.getAmount() > 0;
    }

    public void reduceQuantityInStock(BookDto bookDto){
        Book book = searchBookById(bookDto.getId());

        if(book != null){
            int amountInStock = book.getAmount();

            if(amountInStock > 0){
                book.setAmount(amountInStock - 1);
                saveBook(book);
                logger.info("Quantity reduce in stock of book with ID: " + book.getId());
            }else{
                throw new GenericNotFoundException("Quantity insufficient in stock of the book with ID: " + book.getId());
            }
        }else{
            throw new GenericNotFoundException("The book not found with ID: " + book.getId());
        }
    }

    private Book searchBookById(Long id){
        return bookRepository.findById(id).orElse(null);
    }

    private void saveBook(Book book){
        bookRepository.save(book);
    }
}
