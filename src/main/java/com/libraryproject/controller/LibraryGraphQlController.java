package com.libraryproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.libraryproject.model.Genre;
import com.libraryproject.model.Librarian;
import com.libraryproject.model.User;
import com.libraryproject.service.GenreService;
import com.libraryproject.service.LibrarianService;
import com.libraryproject.service.UserService;

@Controller
public class LibraryGraphQlController {

    @Autowired
    private UserService userService;

    @Autowired
    private GenreService genreService;

    @Autowired LibrarianService librarianService;

    // Queries para User
    @QueryMapping(name = "getAllUsers")
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    @QueryMapping(name = "getUserById")
    public User getUserById(@Argument Integer idUser) {
        return userService.getByIdUser(idUser);
    }

    // Mutations para User
    @MutationMapping(name = "addUser")
    public User addUser(@Argument User user) {
        userService.save(user);
        return user;
    }

    @MutationMapping(name = "updateUser")
    public User updateUser(@Argument Integer idUser, @Argument User user) {
        User auxUser = userService.getByIdUser(idUser);
        user.setIdUser(auxUser.getIdUser());
        return userService.save(user);
    }

    // Queries para Genre
    @QueryMapping(name = "getAllGenres")
    public List<Genre> getAllGenres() {
        return genreService.getAll();
    }

    @QueryMapping(name = "getGenreById")
    public Genre getGenreById(@Argument Integer idGenre) {
        return genreService.getByIdGenre(idGenre);
    }

    // Mutations para Genre
    @MutationMapping(name = "addGenre")
    public Genre addGenre(@Argument Genre genre) {
        genreService.save(genre);
        return genre;
    }

    @MutationMapping(name = "updateGenre")
    public Genre updateGenre(@Argument Integer idGenre, @Argument Genre genre) {
        Genre auxGenre = genreService.getByIdGenre(idGenre);
        genre.setIdGenre(auxGenre.getIdGenre());
        return genreService.save(genre);
    }

    @MutationMapping(name = "deleteGenre")
    public String deleteGenre(@Argument Integer idGenre) {
        genreService.delete(idGenre);
        return "Delete Record";
    }

    //Queries para librarians
    @QueryMapping(name = "getAllLibrarians")
    public List<Librarian> getAllLibrarians() {
        return librarianService.getAll();
    }

    @QueryMapping(name = "getLibrarianById")
    public Librarian getLibrarianById(@Argument Integer idLibrarian) {
        return librarianService.getById(idLibrarian);
    }

    //Mutations para librarians
    @MutationMapping(name = "addLibrarian")
    public Librarian addLibrarian(@Argument Librarian librarian) {
        return librarianService.save(librarian);
    }

    @MutationMapping(name = "updateLibrarian")
    public Librarian updateLibrarian(@Argument Integer idLibrarian, @Argument Librarian librarian) {
        Librarian auxLibrarian = librarianService.getById(idLibrarian);
        if (auxLibrarian == null) {
            throw new RuntimeException("Librarian not found with ID: " + idLibrarian);
        }

        auxLibrarian.setLibrarianName(librarian.getLibrarianName());
        auxLibrarian.setFirstSurname(librarian.getFirstSurname());
        auxLibrarian.setSecondSurname(librarian.getSecondSurname());
        auxLibrarian.setEmail(librarian.getEmail());
        auxLibrarian.setPassword(librarian.getPassword());
        auxLibrarian.setPhone(librarian.getPhone());

        return librarianService.save(auxLibrarian);
    }

    @MutationMapping(name = "deleteLibrarian")
    public String deleteLibrarian(@Argument Integer idLibrarian) {
        librarianService.delete(idLibrarian);
        return "Librarian deleted successfully";
    }
}
