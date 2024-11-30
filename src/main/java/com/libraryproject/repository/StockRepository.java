package com.libraryproject.repository;

//For use in MongoDB
//import org.springframework.data.mongodb.repository.MongoRepository;

//For use in MySQL
import org.springframework.data.jpa.repository.JpaRepository;

import com.libraryproject.model.Stock;

//For use in MongoDB
    //public interface StockRepository extends MongoRepository<Stock, Integer>{
    
//For use in MySQL
    public interface StockRepository extends JpaRepository<Stock, Integer>{

}