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
import com.libraryproject.model.LibraryPenalty;
import com.libraryproject.service.LibraryPenaltyService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("libraryPenalties")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@Tag(name = "Library Penalty", description = "Provides methods for the control of library penalties.")
public class LibraryPenaltyController {

    @Autowired
	private LibraryPenaltyService service;

	//GET All Library Penalities
	@Operation(summary="Get all library penalities.")
    @ApiResponse(responseCode="200", description="Found library penalities.", content={
        @Content(mediaType="application/json", array=@ArraySchema(schema=@Schema(implementation=LibraryPenalty.class)))   
    })
	@GetMapping
	public List<LibraryPenalty> getAll() {
		return service.getAll();
	}

	//GET One Registry (Library Penality)
	@Operation(summary = "Get a library penality registry through Id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found penality registry.", content = @Content(schema = @Schema(implementation = LibraryPenalty.class))),
            @ApiResponse(responseCode = "400", description = "Bad request.", content = @Content(schema = @Schema(implementation = ExceptionHandlerAdvice.class))),
			@ApiResponse(responseCode = "404", description = "The stock registry requested doesn't exists.", content = @Content(schema = @Schema(implementation = ExceptionHandlerAdvice.class))),
    })
		//For use with MongoDB:
	// @GetMapping("{id}")
	// public ResponseEntity<LibraryPenalty> getByIdLibraryPenalty(@PathVariable Integer id) {
	// 	LibraryPenalty libraryPenalty = service.getByIdLibraryPenalty(id);
	// 	return new ResponseEntity<LibraryPenalty>(libraryPenalty, HttpStatus.OK);
	// }
		//For use with MySQL:
	@GetMapping("{idLibraryPenalty}")
	public ResponseEntity<LibraryPenalty> getByIdLibraryPenalty(@PathVariable int idLibraryPenalty) {
		LibraryPenalty libraryPenalty = service.getByIdLibraryPenalty(idLibraryPenalty);
		return new ResponseEntity<>(libraryPenalty, HttpStatus.OK);
	}

	//POST New Registry (Library Penality)
	@Operation(summary = "Add new library penality registry.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully registered library penality.", content = @Content(schema = @Schema(implementation = LibraryPenalty.class))),
            @ApiResponse(responseCode = "400", description = "Bad request.", content = @Content(schema = @Schema(implementation = ExceptionHandlerAdvice.class)))
    })
	@PostMapping
	public void register(@RequestBody LibraryPenalty libraryPenalty) {
		service.save(libraryPenalty);
	}

	//PUT Update a Library Penality registry
	@Operation(summary = "Update the library penality")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated library penality.",content = @Content(schema = @Schema(implementation = LibraryPenalty.class))),
			@ApiResponse(responseCode = "400", description = "Bad request.", content = @Content(schema = @Schema(implementation = ExceptionHandlerAdvice.class))),
            @ApiResponse(responseCode = "404", description = "The library penality registry requested doesn't exists.",content = @Content(schema = @Schema(implementation = ExceptionHandlerAdvice.class)))
    })
		//For use with MongoDB:
	// @PutMapping("{id}")
	// public ResponseEntity<?> update(@RequestBody LibraryPenalty libraryPenalty, @PathVariable Integer id) {
	// 	LibraryPenalty auxLibraryPenalty = service.getByIdLibraryPenalty(id);
	// 	libraryPenalty.setId(auxLibraryPenalty.getId());
	// 	service.save(libraryPenalty);
	// 	return new ResponseEntity<String>("Updated Record", HttpStatus.OK);
	// }
		//For use with MySQL:
	@PutMapping("{idLibraryPenalty}")
	public ResponseEntity<?> update(@RequestBody LibraryPenalty libraryPenalty, @PathVariable int idLibraryPenalty) {
		LibraryPenalty auxLibraryPenalty = service.getByIdLibraryPenalty(idLibraryPenalty);
		libraryPenalty.setIdlibraryPenalty(auxLibraryPenalty.getIdlibraryPenalty());
		service.save(libraryPenalty);
		return new ResponseEntity<>("Updated Record", HttpStatus.OK);
	}

	//DELETE Library Penality
	@Operation(summary = "Delete library penality")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "This library penality registry was removed from the database.", content = @Content(schema = @Schema(implementation = LibraryPenalty.class))),
            @ApiResponse(responseCode = "400", description = "Bad request.", content = @Content(schema = @Schema(implementation = ExceptionHandlerAdvice.class))),
			@ApiResponse(responseCode = "404", description = "The library penality registry requested doesn't exists.", content = @Content(schema = @Schema(implementation = ExceptionHandlerAdvice.class)))
    })
		//For use with MongoDB:
	// @DeleteMapping("{id}")
	// public void delete(@PathVariable Integer id) {
	// 	service.delete(id);
	// }
		//For use with MySQL:
    @DeleteMapping("{idLibraryPenalty}")
	public void delete(@PathVariable int idLibraryPenalty) {
		service.delete(idLibraryPenalty);
	}
}
