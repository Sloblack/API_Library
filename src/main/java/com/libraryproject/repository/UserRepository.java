package com.libraryproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.libraryproject.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    
}