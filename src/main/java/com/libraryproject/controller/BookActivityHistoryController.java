package com.libraryproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.libraryproject.exception.ExceptionHandlerAdvice;
import com.libraryproject.model.Author;
import com.libraryproject.model.BookActivityHistory;
import com.libraryproject.service.BookActivityHistoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("historyBooks")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
        RequestMethod.PUT })
@Tag(name="BookActivityHistoryController", description="Provides methods for managing book history activities")
public class BookActivityHistoryController {
    @Autowired
    public BookActivityHistoryService bookActivityHistoryService;

    @Operation(summary="Get all book history")
    @ApiResponse(responseCode="200", description="Found records", content={
            @Content(mediaType="application/json", array=@ArraySchema(schema=@Schema(implementation=Author.class)))
    })
    @GetMapping
    public List<BookActivityHistory> getAll(){
        return bookActivityHistoryService.getAll();
    }


    @Operation(summary = "Gets record using id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the record", content = @Content(schema = @Schema(implementation = BookActivityHistory.class))),
            @ApiResponse(responseCode = "404", description = "record doesn't exits", content = @Content(schema = @Schema(implementation = ExceptionHandlerAdvice.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ExceptionHandlerAdvice.class)))
    })
    @GetMapping("{id}")
    public ResponseEntity<BookActivityHistory> getById(@PathVariable Integer id){
        BookActivityHistory history = bookActivityHistoryService.findById(id);
        return  new ResponseEntity<>(history, HttpStatus.OK);
    }

}
