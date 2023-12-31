package com.facol.bookstore.repositories;

import com.facol.bookstore.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Book findByTitleAndAuthor(String title, String author);
}
