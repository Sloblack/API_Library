package com.libraryproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.libraryproject.model.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Integer>{
    
}