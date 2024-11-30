package com.libraryproject.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idnotifications")
    private int idNotifications;

    @ManyToOne
    @JoinColumn(name = "iduser", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "typenotification", length = 50)
    private TypeNotification typeNotification;

    @Column(name = "messagenotification")
    private String message;

    @Column(name = "sentdate")
    private LocalDateTime sentdate;

    public Notification() {
    }

    public Notification(int idNotifications, User user, TypeNotification typeNotification, String message,
            LocalDateTime sentdate) {
        this.idNotifications = idNotifications;
        this.user = user;
        this.typeNotification = typeNotification;
        this.message = message;
        this.sentdate = sentdate;
    }

    public int getIdNotifications() {
        return idNotifications;
    }

    public void setIdNotifications(int idNotifications) {
        this.idNotifications = idNotifications;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TypeNotification getTypeNotification() {
        return typeNotification;
    }

    public void setTypeNotification(TypeNotification typeNotification) {
        this.typeNotification = typeNotification;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getSentdate() {
        return sentdate;
    }

    public void setSentdate(LocalDateTime sentdate) {
        this.sentdate = sentdate;
    }

    public enum TypeNotification {
        loan_due_date,
        fine_pending_payment
    }

    @Override
    public String toString() {
        return "Notification [idNotifications=" + idNotifications + ", user=" + user + ", typeNotification="
                + typeNotification + ", message=" + message + ", sentdate=" + sentdate + "]";
    }
    
}