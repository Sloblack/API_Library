package com.libraryproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libraryproject.model.Book;
import com.libraryproject.repository.BookRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class BookService {
    @Autowired
	private BookRepository repo;

	public List<Book> getAll() {
		return repo.findAll();
	}

	public void save(Book book) {
		repo.save(book);
	}
		//For use with MongoDB:
	// public Book getByIdBook(Integer id) {
	// 	return repo.findById(id).get();
	// }

	// public void delete(Integer id) {
	// 	repo.deleteById(id);
	// }

		//For use with MySQL:
	public Book getByIdBook(Integer idBook) {
		return repo.findById(idBook).get();
	}

	public void delete(Integer idBook) {
		repo.deleteById(idBook);
	}
}

