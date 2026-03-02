package com.kaizen.library.repository;

import com.kaizen.library.model.BookCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookCodeRepository extends JpaRepository<BookCode, Long> {
    Optional<BookCode> findBookCodeByIdAndIsAvailable(Long id, Boolean isAvailable);
}
