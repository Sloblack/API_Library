package com.libraryproject.repository;

//For use in MongoDB
//import org.springframework.data.mongodb.repository.MongoRepository;

//For use in MySQL
import org.springframework.data.jpa.repository.JpaRepository;

import com.libraryproject.model.Book;

//For use in MongoDB
    //public interface BookRepository extends MongoRepository<Book, Integer>{
    
//For use in MySQL
    public interface BookRepository extends JpaRepository<Book, Integer>{
}