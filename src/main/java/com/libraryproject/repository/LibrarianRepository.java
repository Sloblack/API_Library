package com.libraryproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.libraryproject.model.Librarian;

import java.util.List;

@Repository
public interface LibrarianRepository extends JpaRepository<Librarian, Integer>{

    public List<Librarian> getByLibrarianName(String librarianName);

}
