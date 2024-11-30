package com.libraryproject.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.libraryproject.model.Publisher;
import com.libraryproject.service.PublisherService;

@RestController
@RequestMapping("publisher")
@CrossOrigin(origins = "http://localhost:3000", methods= {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class PublisherController {

    @Autowired
	private PublisherService service;

    @GetMapping
	public List<Publisher> getAll() {
		return service.getAll();
	}

    @GetMapping("{idPublisher}")
    public ResponseEntity<Publisher> getByIdPublisher(@PathVariable int idPublisher) {
		Publisher publisher = service.getByIdPublisher(idPublisher);
		return new ResponseEntity<>(publisher, HttpStatus.OK);
	}

    @PostMapping
	public void register(@RequestBody Publisher publisher) {
		service.save(publisher);
	}

	@PutMapping("{idPublisher}")
	public ResponseEntity<?> update(@RequestBody Publisher publisher, @PathVariable int idPublisher) {
		try {
			Publisher auxPublisher = service.getByIdPublisher(idPublisher);
			publisher.setIdPublisher(auxPublisher.getIdPublisher());
			service.save(publisher);
			return new ResponseEntity<>("Updated record", HttpStatus.OK);
		}catch (NoSuchElementException e) {
		return new ResponseEntity<>("The record with the control number provided is not found in the database", HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("{idPublisher}")
	public void delete(@PathVariable int idPublisher) {
		service.delete(idPublisher);
	}
}
