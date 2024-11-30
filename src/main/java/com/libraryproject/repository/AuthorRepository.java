package com.libraryproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.libraryproject.model.Author;

import java.util.List;

@Repository
    public interface AuthorRepository extends  JpaRepository<Author, Integer>{

    public List<Author> findByAuthorName(String AuthorName);

    public  List<Author> findByNationality(String nationality);

}
