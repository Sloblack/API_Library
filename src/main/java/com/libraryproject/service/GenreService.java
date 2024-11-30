package com.libraryproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libraryproject.model.Genre;
import com.libraryproject.repository.GenreRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class GenreService {
    
    @Autowired
    private GenreRepository genreRepository;

    public List<Genre> getAll(){
        return genreRepository.findAll();
    }

    public Genre save(Genre genre){
        return genreRepository.save(genre);
    }

    public Genre getByIdGenre(Integer idGenre){
        return genreRepository.findById(idGenre).get();
    }

    public void delete(Integer idGenre){
        genreRepository.deleteById(idGenre);
    }
}