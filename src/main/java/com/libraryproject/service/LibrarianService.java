package com.libraryproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.libraryproject.model.Librarian;
import com.libraryproject.repository.LibrarianRepository;

@Service
public class LibrarianService {
    
    @Autowired
    private LibrarianRepository librarianRepository;

    public List<Librarian> getAll(){
        return librarianRepository.findAll();
    }

    public Librarian save(Librarian librarian){
        return librarianRepository.save(librarian);
    }

    public Librarian getById(Integer idLibrarian){
        return librarianRepository.findById(idLibrarian).get();
    }

    public void delete(Integer id) {
		librarianRepository.deleteById(id);
	}

    public List<Librarian> getByLibrarianName(String librarianName){
        return librarianRepository.getByLibrarianName(librarianName);
    }

}
