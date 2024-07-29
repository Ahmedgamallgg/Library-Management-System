package com.booklib.booklib.Service;

import com.booklib.booklib.Repositories.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.booklib.booklib.Entities.Book;
import org.springframework.cache.annotation.Cacheable;


import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    @Cacheable("books")
    public List<Book> getAllBooks() {
        return  bookRepository.findAll();
    }
    @Cacheable("book")
    public Book getBookById(long id) {
        return bookRepository.findById(id).orElse(null);
    }
    @Transactional
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }
    @Transactional
    public Book updateBook(Long id, Book bookDetails) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            book.setTitle(bookDetails.getTitle());
            book.setAuthor(bookDetails.getAuthor());
            book.setPublicationYear(bookDetails.getPublicationYear());
            book.setIsbn(bookDetails.getIsbn());
            return bookRepository.save(book);
        } else {

            throw new RuntimeException("Book not found with id " + id);
        }
    }
    @Transactional
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
