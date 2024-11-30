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
import com.libraryproject.model.Book;
import com.libraryproject.service.BookService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("books")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT })
@Tag(name = "Book", description = "Provides methods for books control.")
public class BookController {

	@Autowired
	private BookService service;

	//GET All Books
	@Operation(summary="Get all books.")
    @ApiResponse(responseCode="200", description="Found books.", content={
        @Content(mediaType="application/json", array=@ArraySchema(schema=@Schema(implementation=Book.class)))   
    })
	@GetMapping
	public List<Book> getAll() {
		return service.getAll();
	}

	//GET One Registry (Book)
	@Operation(summary = "Get a book through Id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found book.", content = @Content(schema = @Schema(implementation = Book.class))),
            @ApiResponse(responseCode = "400", description = "Bad request.", content = @Content(schema = @Schema(implementation = ExceptionHandlerAdvice.class))),
			@ApiResponse(responseCode = "404", description = "The book requested doesn't exists.", content = @Content(schema = @Schema(implementation = ExceptionHandlerAdvice.class))),
    })
		//For use with MongoDB:
	// @GetMapping("{id}")
	// public ResponseEntity<?> getByIdBook(@PathVariable Integer id) {
	// 	Book book = service.getByIdBook(id);
	// 	return new ResponseEntity<Book>(book, HttpStatus.OK);
	// }
		//For use with MySQL:
	@GetMapping("{idBook}")
	public ResponseEntity<Book> getByIdBook(@PathVariable Integer idBook) {
		Book book = service.getByIdBook(idBook);
		return new ResponseEntity<>(book, HttpStatus.OK);
	}

	//POST New Registry (Book)
	@Operation(summary = "Add new books.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully registered book.", content = @Content(schema = @Schema(implementation = Book.class))),
            @ApiResponse(responseCode = "400", description = "Bad request.", content = @Content(schema = @Schema(implementation = ExceptionHandlerAdvice.class)))
    })
	@PostMapping
	public void register(@RequestBody Book book) {
		service.save(book);
	}

	//PUT Update a Book
	@Operation(summary = "Update book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated book.",content = @Content(schema = @Schema(implementation = Book.class))),
			@ApiResponse(responseCode = "400", description = "Bad request.", content = @Content(schema = @Schema(implementation = ExceptionHandlerAdvice.class))),
            @ApiResponse(responseCode = "404", description = "The book requested doesn't exists.",content = @Content(schema = @Schema(implementation = ExceptionHandlerAdvice.class)))
    })
		//For use with MongoDB:
	// @PutMapping("{id}")
	// public ResponseEntity<?> update(@RequestBody Book book, @PathVariable Integer id) {
	// 	Book auxBook = service.getByIdBook(id);
	// 	book.setId(auxBook.getId());
	// 	service.save(book);
	// 	return new ResponseEntity<String>("Updated record", HttpStatus.OK);
	// }
		//For use with MySQL:
	@PutMapping("{idBook}")
	public ResponseEntity<?> update(@RequestBody Book book, @PathVariable Integer idBook) {
		Book auxBook = service.getByIdBook(idBook);
		book.setIdBook(auxBook.getIdBook());
		service.save(book);
		return new ResponseEntity<>("Updated record", HttpStatus.OK);
	}

	//DELETE Book
	@Operation(summary = "Delete book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "This book was removed from the database.", content = @Content(schema = @Schema(implementation = Book.class))),
            @ApiResponse(responseCode = "400", description = "Bad request.", content = @Content(schema = @Schema(implementation = ExceptionHandlerAdvice.class))),
			@ApiResponse(responseCode = "404", description = "The book requested doesn't exists.", content = @Content(schema = @Schema(implementation = ExceptionHandlerAdvice.class)))
    })
		//For use with MongoDB:
	// @DeleteMapping("{id}")
	// public void delete(@PathVariable Integer id) {
	// 	service.delete(id);
	// }
		//For use with MySQL:
	@DeleteMapping("{idBook}")
	public void delete(@PathVariable Integer idBook) {
		service.delete(idBook);
	}
}
