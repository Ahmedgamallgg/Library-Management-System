package com.booklib.booklib.Repositories;

import com.booklib.booklib.Entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookRepository extends JpaRepository<Book, Long> {

}
