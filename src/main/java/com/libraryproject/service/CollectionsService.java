package com.libraryproject.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libraryproject.model.Collections;
import com.libraryproject.repository.CollectionsRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CollectionsService {
    @Autowired
    private CollectionsRepository repo;

    public List<Collections> getAll(){
        return repo.findAll();
    }

    public void save (Collections collections){
        repo.save(collections);
    }

    public Collections getByIdCollection(Integer idCollection){
        return repo.findById(idCollection).get();
    }

    public void delete (Integer idCollection){
        repo.deleteById(idCollection);
    }

}
