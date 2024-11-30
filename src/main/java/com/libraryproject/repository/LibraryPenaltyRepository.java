package com.libraryproject.repository;

//For use in MongoDB
//import org.springframework.data.mongodb.repository.MongoRepository;

//For use in MySQL
import org.springframework.data.jpa.repository.JpaRepository;

import com.libraryproject.model.LibraryPenalty;

//For use in MongoDB
    //public interface LibraryPenaltyRepository extends MongoRepository<LibraryPenalty, Integer>{
    
//For use in MySQL
    public interface LibraryPenaltyRepository extends JpaRepository<LibraryPenalty, Integer>{

}