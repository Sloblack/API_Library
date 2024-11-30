package com.libraryproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libraryproject.model.User;
import com.libraryproject.repository.UserRepository;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User save(User user){
        return userRepository.save(user);
    }

    public User getByIdUser(Integer idUser){
        return userRepository.findById(idUser).get();
    }
}