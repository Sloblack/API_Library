package com.libraryproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.libraryproject.model.Loans;

public interface LoansRepository extends JpaRepository<Loans, Integer> {

}
