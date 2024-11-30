package com.libraryproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libraryproject.model.Notification;
import com.libraryproject.repository.NotificationRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    public List<Notification> getAll(){
        return notificationRepository.findAll();
    }

    public void save(Notification notification){
        notificationRepository.save(notification);
    }

    public Notification getByIdNotification(Integer idNotification){
        return notificationRepository.findById(idNotification).get();
    }

    public void delete(Integer idNotification){
        notificationRepository.deleteById(idNotification);
    }
}