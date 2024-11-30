package com.libraryproject.service;


import com.libraryproject.model.BookActivityHistory;
import com.libraryproject.repository.BookActivityHistoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class BookActivityHistoryService {
    @Autowired
    private BookActivityHistoryRepository bookActivityHistoryRepository;

    public List<BookActivityHistory> getAll(){
        return bookActivityHistoryRepository.findAll();
    }

    public BookActivityHistory findById(Integer id){
        return bookActivityHistoryRepository.findById(id).get();
    }

}
