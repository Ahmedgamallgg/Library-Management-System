package com.booklib.booklib.Service;

import com.booklib.booklib.Entities.Book;
import com.booklib.booklib.Entities.BorrowingRecord;
import com.booklib.booklib.Entities.Patron;
import com.booklib.booklib.Exceptions.ResourceNotFoundException;
import com.booklib.booklib.Repositories.BookRepository;
import com.booklib.booklib.Repositories.BorrowingRepository;
import com.booklib.booklib.Repositories.PatronRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BorrowingRecordService {

    private final BookRepository bookRepository;
    private final PatronRepository patronRepository;
    private final BorrowingRepository borrowingRecordRepository;

    @Autowired
    public BorrowingRecordService(BookRepository bookRepository, PatronRepository patronRepository, BorrowingRepository borrowingRecordRepository) {
        this.bookRepository = bookRepository;
        this.patronRepository = patronRepository;
        this.borrowingRecordRepository = borrowingRecordRepository;
    }
    @Transactional
    public BorrowingRecord borrowBook(Long bookId, Long patronId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + bookId));

        Patron patron = patronRepository.findById(patronId)
                .orElseThrow(() -> new ResourceNotFoundException("Patron not found with id " + patronId));

        BorrowingRecord borrowingRecord = new BorrowingRecord();
        borrowingRecord.setBook(book);
        borrowingRecord.setPatron(patron);
        borrowingRecord.setBorrowingDate(LocalDateTime.now());

        return borrowingRecordRepository.save(borrowingRecord);
    }
    @Transactional
    public BorrowingRecord returnBook(Long bookId, Long patronId) {
        Optional<Patron> patronOptional = patronRepository.findById(patronId);
        Optional<Book> bookOptional = bookRepository.findById(bookId);

        if (patronOptional.isEmpty() || bookOptional.isEmpty()) {
            throw new ResourceNotFoundException("No active borrowing record found for book id " + bookId + " and patron id " + patronId);
        }

        Book book = bookOptional.get();
        Patron patron = patronOptional.get();

        BorrowingRecord borrowingRecord = new BorrowingRecord();
        borrowingRecord.setBook(book);
        borrowingRecord.setPatron(patron);
        borrowingRecord.setBorrowingDate(LocalDate.now().atStartOfDay());
        return borrowingRecordRepository.save(borrowingRecord);

    }

    @Cacheable("borrowingRecords")

    public List<BorrowingRecord> getAllBorrowingRecords() {
        return borrowingRecordRepository.findAll();
    }
    @Cacheable("borrowingRecord")

    public Optional<BorrowingRecord> getBorrowingRecordById(Long id) {
        return borrowingRecordRepository.findById(id);
    }
}
