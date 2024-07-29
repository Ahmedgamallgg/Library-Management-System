package com.booklib.booklib.Repositories;

import com.booklib.booklib.Entities.BorrowingRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowingRepository  extends JpaRepository<BorrowingRecord, Long> {
}
