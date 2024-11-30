package com.libraryproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.libraryproject.model.Collections;

public interface CollectionsRepository extends JpaRepository<Collections, Integer> {

}
