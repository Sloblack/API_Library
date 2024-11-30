package com.libraryproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libraryproject.model.Author;
import com.libraryproject.repository.AuthorRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AuthorService {
    
    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> getAll(){
        return authorRepository.findAll();
    }

    public Author save(Author author){
        authorRepository.save(author);
        return author;
    }

    public Author getById(Integer id){
        return authorRepository.findById(id).get();
    }

    public void delete(Integer id){
        authorRepository.deleteById(id);
    }

    public List<Author> findByAuthorName(String authorName){return authorRepository.findByAuthorName(authorName);}

    public List<Author> findByNationality(String nationality){return authorRepository.findByNationality(nationality);}

}

