package com.libraryproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.libraryproject.model.Genre;

public interface GenreRepository extends JpaRepository<Genre, Integer>{
    
}