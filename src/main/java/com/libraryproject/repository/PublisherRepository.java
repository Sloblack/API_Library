package com.libraryproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.libraryproject.model.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher, Integer>{

}
