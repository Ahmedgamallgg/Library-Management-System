package com.booklib.booklib.Repositories;

import com.booklib.booklib.Entities.Patron;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatronRepository extends JpaRepository<Patron, Long> {
}
