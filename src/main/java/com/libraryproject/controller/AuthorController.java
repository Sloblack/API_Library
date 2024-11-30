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

import com.libraryproject.dto.AuthorRequestDTO;
import com.libraryproject.exception.ExceptionHandlerAdvice;
import com.libraryproject.model.Author;
import com.libraryproject.service.AuthorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("authors")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT })
@Tag(name = "Authors", description = "Provides methods for managing Authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @Operation(summary = "Get all authors")
    @ApiResponse(responseCode = "200", description = "Authors retrieved successfully", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Author.class)))
    })
    @GetMapping
    public ResponseEntity<List<Author>> getAll() {
        List<Author> authors = authorService.getAll();
        return ResponseEntity.ok(authors);
    }

    @Operation(summary = "Get author by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Author found", content = @Content(schema = @Schema(implementation = Author.class))),
            @ApiResponse(responseCode = "404", description = "Author not found", content = @Content(schema = @Schema(implementation = ExceptionHandlerAdvice.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ExceptionHandlerAdvice.class)))
    })
    @GetMapping("{idAuthor}")
    public ResponseEntity<Author> getById(@PathVariable Integer idAuthor) {
        Author author = authorService.getById(idAuthor);
        return ResponseEntity.ok(author);
    }

    @Operation(summary = "Add a new author")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Author created successfully", content = @Content(schema = @Schema(implementation = Author.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ExceptionHandlerAdvice.class)))
    })
    @PostMapping
    public ResponseEntity<String> add(@RequestBody AuthorRequestDTO authorRequestDTO) {
        Author author = mapToAuthor(authorRequestDTO);
        authorService.save(author);
        return ResponseEntity.status(HttpStatus.CREATED).body("Author saved successfully");
    }

    @Operation(summary = "Update an existing author")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Author updated successfully", content = @Content(schema = @Schema(implementation = Author.class))),
            @ApiResponse(responseCode = "404", description = "Author not found", content = @Content(schema = @Schema(implementation = ExceptionHandlerAdvice.class)))
    })
    @PutMapping("{idAuthor}")
    public ResponseEntity<String> updateAuthor(@PathVariable Integer idAuthor, @RequestBody AuthorRequestDTO authorRequestDTO) {
        Author existingAuthor = authorService.getById(idAuthor);
        if (existingAuthor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author not found");
        }
        Author updatedAuthor = mapToAuthor(authorRequestDTO);
        updatedAuthor.setIdAuthor(existingAuthor.getIdAuthor());
        authorService.save(updatedAuthor);
        return ResponseEntity.ok("Author updated successfully");
    }

    @Operation(summary = "Delete an author")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Author deleted successfully", content = @Content(schema = @Schema(implementation = Author.class))),
            @ApiResponse(responseCode = "404", description = "Author not found", content = @Content(schema = @Schema(implementation = ExceptionHandlerAdvice.class)))
    })
    @DeleteMapping("{idAuthor}")
    public ResponseEntity<String> deleteAuthor(@PathVariable Integer idAuthor) {
        authorService.delete(idAuthor);
        return ResponseEntity.ok("Author deleted successfully");
    }

    @Operation(summary = "Search authors by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Authors found", content = @Content(schema = @Schema(implementation = Author.class))),
            @ApiResponse(responseCode = "404", description = "Authors not found", content = @Content(schema = @Schema(implementation = ExceptionHandlerAdvice.class)))
    })
    @GetMapping("/searchName/{authorName}")
    public ResponseEntity<List<Author>> getByName(@PathVariable String authorName) {
        List<Author> authors = authorService.findByAuthorName(authorName);
        return ResponseEntity.ok(authors);
    }

    @Operation(summary = "Search authors by nationality")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Authors found", content = @Content(schema = @Schema(implementation = Author.class))),
            @ApiResponse(responseCode = "404", description = "Authors not found", content = @Content(schema = @Schema(implementation = ExceptionHandlerAdvice.class)))
    })
    @GetMapping("/searchNationality/{nationality}")
    public ResponseEntity<List<Author>> getByNationality(@PathVariable String nationality) {
        List<Author> authors = authorService.findByNationality(nationality);
        return ResponseEntity.ok(authors);
    }

    // Custom mapping method
    private Author mapToAuthor(AuthorRequestDTO dto) {
        Author author = new Author();
        author.setAuthorName(dto.getAuthorName());
        author.setNationality(dto.getNationality());
        // Map other properties as necessary
        return author;
    }
}


/*package com.libraryproject.controller;

import java.util.List;

import com.libraryproject.dto.AuthorRequestDTO;
import org.modelmapper.ModelMapper;
import com.libraryproject.exception.ExceptionHandlerAdvice;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.libraryproject.model.Author;
import com.libraryproject.service.AuthorService;

import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;


import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@RequestMapping("authors")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
		RequestMethod.PUT })
@Tag(name="Authors", description="Provides methods for managing Authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private ModelMapper modelMapper;

    @Operation(summary="Get all authors")
    @ApiResponse(responseCode="200", description="Found authors", content={
        @Content(mediaType="application/json", array=@ArraySchema(schema=@Schema(implementation=Author.class)))   
    })
    @GetMapping
    public List<Author> getAll(){
        return authorService.getAll();
    }


    @Operation(summary = "Gets author using id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the author", content = @Content(schema = @Schema(implementation = Author.class))),
            @ApiResponse(responseCode = "404", description = "author doesn't exits", content = @Content(schema = @Schema(implementation = ExceptionHandlerAdvice.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ExceptionHandlerAdvice.class)))
    })
    @GetMapping("{idAuthor}")
    public ResponseEntity<?> getById(@PathVariable  Integer idAuthor){
        Author author = authorService.getById(idAuthor);
        return new ResponseEntity<Author>(author, HttpStatus.CREATED);
    }

    //	@Operation(summary = "Register a manufacturing order")
    //	@ApiResponse(responseCode = "201", description = "Registered manufacturing order", content = {
    //			@Content(mediaType = "application/json", schema = @Schema(implementation = ManufacturingOrder.class)) })
    //	@PostMapping
    //	public ResponseEntity<ManufacturingOrder> add(
    //			@RequestBody ManufacturingOrderRequestDTO manufacturingOrderRequestDTO) {
    //		ManufacturingOrder savedManufacturingOrder = service.save(convertToEntity(manufacturingOrderRequestDTO));
    //		return new ResponseEntity<ManufacturingOrder>(savedManufacturingOrder, HttpStatus.CREATED);
    //	}

    @Operation(summary = "Add new author")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Save record", content = @Content(schema = @Schema(implementation = Author.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ExceptionHandlerAdvice.class)))
    })
    @PostMapping
    public ResponseEntity<?> add(@RequestBody AuthorRequestDTO authorRequestDTO){
        Author author = modelMapper.map(authorRequestDTO, Author.class);
        authorService.save(author);
        return new ResponseEntity<String>("Autor guardado correctamente", HttpStatus.CREATED);
    }

    @Operation(summary = "Update author")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Author updated successfully",content = @Content(schema = @Schema(implementation = Author.class))),
            @ApiResponse(responseCode = "404", description = "Author doesn't exits",content = @Content(schema = @Schema(implementation = ExceptionHandlerAdvice.class)))

    })
    @PutMapping("{id}")
    public ResponseEntity<?> updateAuthor(@PathVariable Integer id ,@RequestBody Author author) {
        Author  auxAuthor = authorService.getById(id);
        author.setIdAuthor(auxAuthor.getIdAuthor());
        authorService.save(author);
        return  new ResponseEntity<String>("Autor guardado correctamente", HttpStatus.OK);
    }

    @Operation(summary = "Delete author")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The author was removed", content = @Content(schema = @Schema(implementation = Author.class))),
            @ApiResponse(responseCode = "404", description = "author doesn't exits", content = @Content(schema = @Schema(implementation = ExceptionHandlerAdvice.class)))
    })
    @DeleteMapping("/{id}")
	public void deleteAuthor(@PathVariable Integer id) {
		authorService.delete(id);
	}

    @Operation(summary = "Search Author by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Author found", content = @Content(schema = @Schema(implementation = Author.class))),
            @ApiResponse(responseCode = "404", description = "Author doesn't exits", content = @Content(schema = @Schema(implementation = ExceptionHandlerAdvice.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ExceptionHandlerAdvice.class)))
    })
    @GetMapping("/searchName/{authorName}")
    public ResponseEntity<List<Author>> getByName(@PathVariable String authorName){
        List<Author> authors = authorService.findByAuthorName(authorName);
        return  new ResponseEntity<List<Author>>(authors, HttpStatus.OK);
    }

    @Operation(summary = "Search Author by nationality")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Author found", content = @Content(schema = @Schema(implementation = Author.class))),
            @ApiResponse(responseCode = "404", description = "author doesn't exits", content = @Content(schema = @Schema(implementation = ExceptionHandlerAdvice.class)))
    })
    @GetMapping("/searchNationality/{nationality}")
    public ResponseEntity<List<Author>> getByNationality(@PathVariable String nationality){
        List<Author> authors = authorService.findByNationality(nationality);
        return  new ResponseEntity<List<Author>>(authors, HttpStatus.OK);
    }

    private Author convertToDTO(AuthorRequestDTO authorRequestDTO){
        return modelMapper.map(authorRequestDTO, Author.class);
    }

}

*/