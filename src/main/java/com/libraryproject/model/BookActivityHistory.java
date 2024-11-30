package com.libraryproject.model;

import java.util.Date;

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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table
public class BookActivityHistory {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int idBookActivityHistory;

    @ManyToOne()
    @JoinColumn(name = "idUser")
    private User user;

    @ManyToOne()
    @JoinColumn(name = "idBook")
    private Book book;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private ActionHistory actionHistory;

    public enum ActionHistory{
        loan,
        returned
    }

    @Column(nullable = false)
    private Date actionHistoryDate;

    @Column(nullable = false)
    private String historyDescription;


}
