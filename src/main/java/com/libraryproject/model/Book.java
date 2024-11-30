package com.libraryproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "books")//, schema = "your_schema")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idBook")
    private int idBook;

    @Column(nullable = false)
    private String idCollection;

    @Column(nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "idAuthor", nullable = false)
    @JsonIgnore
    private Author author;



    @Column( nullable = false)
    private String idGenre;

    @Column(nullable = false)
    private String idPublisher;

    @Column(nullable = false)
    private int publicationYear;

    @Column(nullable = false)
    private String summary;

    @Column(nullable = false)
    private int numberPages;

    @Column(nullable = false)
    private String bookStatus;

    @Column(nullable = false)
    private String acquisitionDate;
}
