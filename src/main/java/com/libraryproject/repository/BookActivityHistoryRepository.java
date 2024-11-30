package com.libraryproject.repository;

import com.libraryproject.model.BookActivityHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookActivityHistoryRepository extends JpaRepository<BookActivityHistory, Integer> {
}
