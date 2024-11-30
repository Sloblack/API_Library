package com.libraryproject.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "collections")
public class Collections {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCollection")
    private int idCollection;

    @Enumerated(EnumType.STRING)
    @Column(name = "collection", length = 20, nullable = false)
    private UserType collectionType;

    public Collections() {
    }

    public Collections(int idCollection, UserType collectionType) {
        this.idCollection = idCollection;
        this.collectionType = collectionType;
    }

    public int getIdCollection() {
        return idCollection;
    }

    public void setIdCollection(int idCollection) {
        this.idCollection = idCollection;
    }

    public UserType getCollectionType() {
        return collectionType;
    }

    public void setCollectionType(UserType collectionType) {
        this.collectionType = collectionType;
    }

    @Override
    public String toString() {
        return "Collections [idCollection=" + idCollection +
        ", collection=" + collectionType + "]"; 

    }
    public enum UserType {
        Al_sol_solito,
        Pasos_de_luna,
        Astrolabio,
        Espejo_de_Urania
    }
}
