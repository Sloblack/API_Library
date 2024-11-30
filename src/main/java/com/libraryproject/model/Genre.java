package com.libraryproject.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idgenre")
    private int idGenre;

    @Column(name = "namegenre", length = 100)
    private String nameGenre;

    public Genre() {
    }

    public Genre(String nameGenre) {
        this.nameGenre = nameGenre;
    }

    public int getIdGenre() {
        return idGenre;
    }

    public void setIdGenre(int idGenre) {
        this.idGenre = idGenre;
    }

    public String getNameGenre() {
        return nameGenre;
    }

    public void setNameGenre(String nameGenre) {
        this.nameGenre = nameGenre;
    }

    @Override
    public String toString() {
        return "Genre [idGenre=" + idGenre + ", nameGenre=" + nameGenre + "]";
    }

}