package com.libraryproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.libraryproject.model.Genre;
import com.libraryproject.service.GenreService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("genres")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@Tag (name ="genres", description = "Provides methods for managing Genre")
public class GenreController {
    @Autowired
    private GenreService service;

    @Operation(summary = "Get All Genres")
    @ApiResponse(responseCode= "200", description = "Found Genres", content = {
        @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Genre.class)))
    })
    @GetMapping
    public List<Genre> getAll(){
        return service.getAll();
    }

    @Operation(summary = "Get Genre By Id")
    @ApiResponse(responseCode = "200", description = "Found Genre", content = {
        @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Genre.class)))
    })
    @GetMapping("{idGenre}")
    public ResponseEntity<Genre> getById(@PathVariable Integer idGenre){
        Genre genre = service.getByIdGenre(idGenre);
        return new ResponseEntity<>(genre, HttpStatus.OK);
    }

    @Operation(summary = "Add New Genre")
    @ApiResponse(responseCode = "200", description = "Add Genre", content = {
        @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Genre.class)))
    })
    @PostMapping
    public void add(@RequestBody Genre genre){
        service.save(genre);
    }

    @Operation(summary = "Update Genre")
    @ApiResponse(responseCode = "200", description = "Update Genre", content = {
        @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Genre.class)))
    })
    @PutMapping("{idGenre}")
    public ResponseEntity<?> update(@RequestBody Genre genre, @PathVariable Integer idGenre){
        Genre auxGenre = service.getByIdGenre(idGenre);
        genre.setIdGenre(auxGenre.getIdGenre());
        service.save(genre);
        return new ResponseEntity<>("Updated record", HttpStatus.OK);
    }

    @Operation(summary = "Delete Genre")
    @ApiResponse(responseCode = "200", description = "Delete Genre", content = {
        @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Genre.class)))
    })
    @DeleteMapping("/{idGenre}")
    public ResponseEntity<?> delete(@PathVariable Integer idGenre){
        service.delete(idGenre);
        return new ResponseEntity<>("Updated record", HttpStatus.OK);
    }
}