package com.libraryproject.repository;

import com.libraryproject.model.AuthorResource;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorResourceRepository extends MongoRepository<AuthorResource, String> {
}
