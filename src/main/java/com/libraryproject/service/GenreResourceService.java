package com.libraryproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.libraryproject.model.GenreResource;
import com.libraryproject.repository.GenreResourceRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class GenreResourceService {
    
    @Autowired
    private GenreResourceRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<GenreResource> getAll(){
        return repository.findAll();
    }

    public void add(GenreResource resource){
        repository.save(resource);
    }

    public Iterable<GenreResource> getByIdGenre(@PathVariable String id) {
        Query query = new Query();
		query.addCriteria(Criteria.where("idGenre").regex("^"+id));
        List<GenreResource> resources = mongoTemplate.find(query, GenreResource.class);
		return resources;
    }

    public void update(GenreResource resource){
        repository.save(resource);
    }

    public void delete(String idGenre){
        repository.findById(idGenre).get();
        repository.deleteById(idGenre);
    }
}
