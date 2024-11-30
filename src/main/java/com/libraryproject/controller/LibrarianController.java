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

import com.libraryproject.exception.ExceptionHandlerAdvice;
import com.libraryproject.model.Librarian;
import com.libraryproject.service.LibrarianService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("librarians")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
		RequestMethod.PUT })
@Tag(name="Librarians", description="Provides methods for managing Librarians")
public class LibrarianController {
    
    @Autowired
    private LibrarianService librarianService;

    @Operation(summary="Get all librarians")
    @ApiResponse(responseCode="200", description="Found librarians", content={
        @Content(mediaType="application/json", array=@ArraySchema(schema=@Schema(implementation=Librarian.class)))   
    })
    @GetMapping
    public List<?> getAll(){
        return librarianService.getAll();
    }

    @Operation(summary = "Gets librarian using the id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the librarian", content = @Content(schema = @Schema(implementation = Librarian.class))),
            @ApiResponse(responseCode = "404", description = "Librarian doesn't exits", content = @Content(schema = @Schema(implementation = ExceptionHandlerAdvice.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ExceptionHandlerAdvice.class)))
    })
    @GetMapping("{idLibrarian}")
	public ResponseEntity<Librarian> getById(@PathVariable Integer idLibrarian) {
		Librarian librarian = librarianService.getById(idLibrarian);
		return new ResponseEntity<>(librarian, HttpStatus.OK);
	}

    @Operation(summary = "Add new Librarian")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Save record", content = @Content(schema = @Schema(implementation = Librarian.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ExceptionHandlerAdvice.class)))
    })
    @PostMapping
    public ResponseEntity<?> save(@RequestBody Librarian librarian){
        librarianService.save(librarian);
        return new ResponseEntity<>("Bibliotecario guardado correctamente", HttpStatus.OK);
    }


    @Operation(summary = "Update librarian")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Librarian updated successfully",content = @Content(schema = @Schema(implementation = Librarian.class))),
            @ApiResponse(responseCode = "404", description = "Librarian doesn't exits",content = @Content(schema = @Schema(implementation = ExceptionHandlerAdvice.class)))

    })
    @PutMapping("{id}")
    public ResponseEntity<?> updateAuthor(@PathVariable Integer id ,@RequestBody Librarian librarian) {
        Librarian  auxLibrarian = librarianService.getById(id);
        librarian.setIdLibrarian(auxLibrarian.getIdLibrarian());
        librarianService.save(librarian);
        return  new ResponseEntity<>("Bibliotecario guardado correctamente", HttpStatus.OK);
    }

    @Operation(summary = "Delete librarian")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The librarian was removed", content = @Content(schema = @Schema(implementation = Librarian.class))),
            @ApiResponse(responseCode = "404", description = "Librarian doesn't exits", content = @Content(schema = @Schema(implementation = ExceptionHandlerAdvice.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ExceptionHandlerAdvice.class)))
    })
    @DeleteMapping("/{idLibrarian}")
    public void delete(Integer idLibrarian){
        librarianService.delete(idLibrarian);
    }

    @Operation(summary = "Search librarian by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Librarian found", content = @Content(schema = @Schema(implementation = Librarian.class))),
            @ApiResponse(responseCode = "404", description = "Librarian doesn't exits", content = @Content(schema = @Schema(implementation = ExceptionHandlerAdvice.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ExceptionHandlerAdvice.class)))
    })
    @GetMapping("/search/{librarianName}")
    public ResponseEntity<List<Librarian>> getByLibrarianName(@PathVariable String librarianName){
            List<Librarian> librarians = librarianService.getByLibrarianName(librarianName);
            return  new ResponseEntity<>(librarians, HttpStatus.OK);
    }
}
