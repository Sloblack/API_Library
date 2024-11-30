package com.libraryproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libraryproject.model.Genre;
import com.libraryproject.model.GenreResource;
import com.libraryproject.service.GenreResourceService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("genreResource")
@Tag (name ="genreResource", description = "Provides methods for managing Genre")
public class GenreResourceController {
    
    @Autowired
    private GenreResourceService service;

    @Operation(summary = "Get All Genres")
    @ApiResponse(responseCode= "200", description = "Found Genres", content = {
        @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Genre.class)))
    })
    @GetMapping()
    public Iterable<GenreResource> getAll(){
        return service.getAll();
    }

    @Operation(summary = "Get Genre By Id")
    @ApiResponse(responseCode = "200", description = "Found Genre", content = {
        @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Genre.class)))
    })
    @GetMapping("{idGenre}")
    public Iterable<GenreResource> searchById(@PathVariable String idGenre){
        return service.getByIdGenre(idGenre);
    }

    @Operation(summary = "Add New Genre")
    @ApiResponse(responseCode = "200", description = "Add Genre", content = {
        @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Genre.class)))
    })
    @PostMapping()
	public ResponseEntity<?> add(@RequestBody GenreResource resource) {
		service.add(resource);
		return new ResponseEntity<>("Saved record", HttpStatus.OK);
	}

    @Operation(summary = "Update Genre")
    @ApiResponse(responseCode = "200", description = "Update Genre", content = {
        @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Genre.class)))
    })
    @PutMapping("/{idGenre}")
	public ResponseEntity<?> update(@RequestBody GenreResource resource, @PathVariable String idGenre) {
		service.update(resource);
		return new ResponseEntity<>("Updated record", HttpStatus.OK);
	}

    @Operation(summary = "Delete Genre")
    @ApiResponse(responseCode = "200", description = "Delete Genre", content = {
        @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Genre.class)))
    })
    @DeleteMapping("/{idGenre}")
	public ResponseEntity<?> delete(@PathVariable String idGenre) {
		service.delete(idGenre);
		return new ResponseEntity<>("Deleted record", HttpStatus.OK);
	}
}