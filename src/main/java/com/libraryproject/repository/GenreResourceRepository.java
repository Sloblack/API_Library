package com.libraryproject.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.libraryproject.model.GenreResource;

@Repository
public interface GenreResourceRepository extends MongoRepository<GenreResource, String> {
    
}
