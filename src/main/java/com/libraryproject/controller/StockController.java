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
//import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.libraryproject.exception.ExceptionHandlerAdvice;
import com.libraryproject.model.Stock;
import com.libraryproject.service.StockService;

@RestController
@RequestMapping("stocks")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@Tag(name = "Stock", description = "Provides methods for stock control")

public class StockController {

    @Autowired
	private StockService service;

	//GET All Books
	@Operation(summary="Get all the stock.")
    @ApiResponse(responseCode="200", description="Found stock.", content={
        @Content(mediaType="application/json", array=@ArraySchema(schema=@Schema(implementation=Stock.class)))   
    })
	@GetMapping
	public List<Stock> getAll() {
		return service.getAll();
	}

	//GET One Registry (Stock)
	@Operation(summary = "Get a stock registry through Id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found stock.", content = @Content(schema = @Schema(implementation = Stock.class))),
            @ApiResponse(responseCode = "400", description = "Bad request.", content = @Content(schema = @Schema(implementation = ExceptionHandlerAdvice.class))),
			@ApiResponse(responseCode = "404", description = "The stock registry requested doesn't exists.", content = @Content(schema = @Schema(implementation = ExceptionHandlerAdvice.class))),
    })
	@GetMapping("{id}")
	public ResponseEntity<Stock> getByIdStock(@PathVariable Integer id) {
		Stock stock = service.getByIdStock(id);
		return new ResponseEntity<Stock>(stock, HttpStatus.OK);
	}

	//POST New Registry (Stock)
	@Operation(summary = "Add new stock registry.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully registered stock.", content = @Content(schema = @Schema(implementation = Stock.class))),
            @ApiResponse(responseCode = "400", description = "Bad request.", content = @Content(schema = @Schema(implementation = ExceptionHandlerAdvice.class)))
    })
	@PostMapping
	public void register(@RequestBody Stock stock) {
		service.save(stock);
	}

	//PUT Update a Stock registry
	@Operation(summary = "Update the stock")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated stock.",content = @Content(schema = @Schema(implementation = Stock.class))),
			@ApiResponse(responseCode = "400", description = "Bad request.", content = @Content(schema = @Schema(implementation = ExceptionHandlerAdvice.class))),
            @ApiResponse(responseCode = "404", description = "The stock registry requested doesn't exists.",content = @Content(schema = @Schema(implementation = ExceptionHandlerAdvice.class)))
    })
		//For use with MongoDB:
	// @PutMapping("{id}")
	// public ResponseEntity<?> update(@RequestBody Stock stock, @PathVariable Integer id) {
	// 	Stock auxStock = service.getByIdStock(id);
	// 	stock.setId(auxStock.getId());
	// 	service.save(stock);
	// 	return new ResponseEntity<String>("Updated record", HttpStatus.OK);
	// }
		//For use with MySQL:
	@PutMapping("{idStock}")
	public ResponseEntity<?> update(@RequestBody Stock stock, @PathVariable int idStock) {
		Stock auxStock = service.getByIdStock(idStock);
		stock.setIdStock(auxStock.getIdStock());
		service.save(stock);
		return new ResponseEntity<String>("Updated record", HttpStatus.OK);
	}

	//DELETE Stock
	@Operation(summary = "Delete stock")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "This stock registry was removed from the database.", content = @Content(schema = @Schema(implementation = Stock.class))),
            @ApiResponse(responseCode = "400", description = "Bad request.", content = @Content(schema = @Schema(implementation = ExceptionHandlerAdvice.class))),
			@ApiResponse(responseCode = "404", description = "The stock registry requested doesn't exists.", content = @Content(schema = @Schema(implementation = ExceptionHandlerAdvice.class)))
    })
		//For use with MongoDB:
	// @DeleteMapping("{id}")
	// public void delete(@PathVariable Integer id) {
	// 	service.delete(id);
	// }
		//For use with MySQL:
	@DeleteMapping("{idStock}")
	public void delete(@PathVariable Integer idStock) {
		service.delete(idStock);
	}
}