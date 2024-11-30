package com.libraryproject.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libraryproject.model.Publisher;
import com.libraryproject.repository.PublisherRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PublisherService {
    @Autowired
    private PublisherRepository repo;

    public List<Publisher> getAll() {
		return repo.findAll();
	}

	public void save(Publisher publisher) {
		repo.save(publisher);
	}

	public Publisher getByIdPublisher(Integer idPublisher) {
		return repo.findById(idPublisher).get();
	}

	public void delete(Integer idPublisher) {
		repo.deleteById(idPublisher);
	}


}
