package com.libraryproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libraryproject.model.LibraryPenalty;
import com.libraryproject.repository.LibraryPenaltyRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class LibraryPenaltyService {
    @Autowired
	private LibraryPenaltyRepository repo;

	public List<LibraryPenalty> getAll() {
		return repo.findAll();
	}

	public void save(LibraryPenalty libraryPenalty) {
		repo.save(libraryPenalty);
	}

		//For use with MongoDB:
	// public LibraryPenalty getByIdLibraryPenalty(Integer id) {
	// 	return repo.findById(id).get();
	// }

	// public void delete(Integer id) {
	// 	repo.deleteById(id);
	// }
		//For use with MySQL:
	public LibraryPenalty getByIdLibraryPenalty(Integer idLibraryPenalty) {
		return repo.findById(idLibraryPenalty).get();
	}
	
	public void delete(Integer idLibraryPenalty) {
		repo.deleteById(idLibraryPenalty);
	}
}
