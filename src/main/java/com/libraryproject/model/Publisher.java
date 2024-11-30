package com.libraryproject.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "publisher")
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPublisher")
    private int idPublisher;

    @Column(name="namePublisher", length = 150 , nullable =false )
    private String namePublisher;

    @Column(name = "country", length = 100)
    private String country;

    public Publisher(){

    }
    
    public Publisher(int idPublisher, String namePublisher, String country){
        this.idPublisher=idPublisher;
        this.namePublisher=namePublisher;
        this.country=country;
    }

    public int getIdPublisher(){
        return idPublisher;
    }

    public void setIdPublisher(int idPublisher){
        this.idPublisher=idPublisher;
    }

    public String getNamePublisher(){
        return namePublisher;
    }

    public void setNamePublisher(String namePublisher){
        this.namePublisher=namePublisher;
    }

    public String getCountry(){
        return country;
    }

    public void setCountry(String country){
        this.country=country;
    }

    @Override
    public String toString(){
        return "Publisher [idPublisher=" + idPublisher + ", namePublisher=" + namePublisher +
        ", country=" + country + "]"; 
    }


}
