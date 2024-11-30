package com.libraryproject.service;

import com.libraryproject.model.Author;
import com.libraryproject.model.AuthorResource;
import com.libraryproject.repository.AuthorResourceRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

@Service
@Transactional
public class AuthorResourceService {
    @Autowired
    private AuthorResourceRepository authorResourceRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<AuthorResource> getAll(){
        return authorResourceRepository.findAll();
    }

    public void save(AuthorResource authorResource){
        authorResourceRepository.save(authorResource);
    }


    public void delete(String id){
        authorResourceRepository.deleteById(id);
    }

    public Iterable<AuthorResource> searchByName(@PathVariable String authorName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("authorName").regex("^"+authorName));
        List<AuthorResource> authorResources = mongoTemplate.find(query, AuthorResource.class);
        return authorResources;
    }


    public void update(AuthorResource authorResource, String id){
        authorResourceRepository.findById(id).get();
        authorResource.setId(id);
        authorResourceRepository.save(authorResource);
    }







}
