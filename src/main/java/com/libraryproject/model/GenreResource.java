package com.libraryproject.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;


@Document(collection = "genre")
public class GenreResource {
    
    @Id
    @Field(targetType=FieldType.OBJECT_ID)
    private String idGenre;
    private String nameGenre;

    public GenreResource() {
    }

    public GenreResource(String idGenre, String nameGenre) {
        this.idGenre = idGenre;
        this.nameGenre = nameGenre;
    }

    public String getIdGenre() {
        return idGenre;
    }

    public void setIdGenre(String idGenre) {
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
        return "GenreResource [idGenre=" + idGenre + ", nameGenre=" + nameGenre + "]";
    }
}