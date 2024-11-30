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

import com.libraryproject.model.Collections;
import com.libraryproject.service.CollectionsService;

@RestController
@RequestMapping("collections")
@CrossOrigin(origins = "http://localhost:3000", methods= {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class CollectionsController {

    @Autowired
	private CollectionsService service;

    @GetMapping
	public List<Collections> getAll() {
		return service.getAll();
	}

    @GetMapping("{idCollection}")
    public ResponseEntity<Collections> getByIdCollection(@PathVariable int idCollection) {
		Collections collections = service.getByIdCollection(idCollection);
		return new ResponseEntity<>(collections, HttpStatus.OK);
	}

    @PostMapping
	public void register(@RequestBody Collections collections) {
		service.save(collections);
	}

	@PutMapping("{idCollection}")
	public ResponseEntity<?> update(@RequestBody Collections collections, @PathVariable int idCollection) {
		try {
			Collections auxCollection = service.getByIdCollection(idCollection);
			collections.setIdCollection(auxCollection.getIdCollection());
			service.save(collections);
			return new ResponseEntity<>("Updated record", HttpStatus.OK);
		}catch (NoSuchElementException e) {
			return new ResponseEntity<>("The record with the control number provided is not found in the database", HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("{idCollection}")
	public void delete(@PathVariable int idCollection) {
		service.delete(idCollection);
	}

}
