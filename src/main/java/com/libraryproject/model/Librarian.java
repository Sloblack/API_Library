package com.libraryproject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name="Librarians")
public class Librarian {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( nullable = false, unique = true)
    private Integer idLibrarian;

    @Column(nullable = false)
    private String librarianName;

    @Column(nullable = false)
    private String firstSurname;

    @Column(nullable = false)
    private String secondSurname;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String phone;

}
