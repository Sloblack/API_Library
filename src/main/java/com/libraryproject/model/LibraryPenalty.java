package com.libraryproject.model;

import java.math.BigDecimal;

//import org.springframework.data.annotation.Id;

    //Imports for MongoDB:
//import org.springframework.data.mongodb.core.mapping.Document;
//import org.springframework.data.mongodb.core.mapping.Field;
//import org.springframework.data.mongodb.core.mapping.FieldType;
    //Imports for MySQL:
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

//For the use of MongoDB, use the next line, and delete/comment the MySQL uses.
//@Document(collection="libraryPenalty")
@Entity
@Table(name="libraryPenalty")
public class LibraryPenalty {
    
    @Id
        //For the use of MongoDB, use the next line for ID, and delete/comment the MySQL uses in model, controller and service folders.
    //private Integer id;
        //Next uses (@GeneratedValue, @Column) for MySQL implementation. 

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idlibraryPenalty")
    private Integer idlibraryPenalty;
    
    @Column(name = "amountPenalty", nullable = false)
    private BigDecimal amountPenalty;

    @Column(name = "emisionDate", nullable = false)
    private String emisionDate;

    @Column(name = "penaltyDescription", columnDefinition = "TEXT", nullable = false)
    private String penaltyDescription;

}