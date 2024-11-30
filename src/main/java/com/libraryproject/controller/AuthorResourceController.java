package com.libraryproject.controller;

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
import com.libraryproject.model.AuthorResource;
import com.libraryproject.service.AuthorResourceService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("authorsResource")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
        RequestMethod.PUT })
@Tag(name="AuthorsResource", description="Provides methods for managing Authors")
public class AuthorResourceController {

    @Autowired
    private AuthorResourceService authorResourceService;

    @Operation(summary="Get all authors")
    @ApiResponse(responseCode="200", description="Found authors", content={
            @Content(mediaType="application/json", array=@ArraySchema(schema=@Schema(implementation= AuthorResource.class)))
    })
    @GetMapping()
    public Iterable<AuthorResource> getAll(){
        return authorResourceService.getAll();
    }

    @Operation(summary = "Search Author by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Author found", content = @Content(schema = @Schema(implementation = AuthorResource.class))),
            @ApiResponse(responseCode = "404", description = "Author doesn't exits", content = @Content(schema = @Schema(implementation = ExceptionHandlerAdvice.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ExceptionHandlerAdvice.class)))
    })
    @GetMapping("{authorName}")
    public Iterable<AuthorResource> searchById(@PathVariable String authorName) {
        return authorResourceService.searchByName(authorName);
    }

    @Operation(summary = "Add new author")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Save record", content = @Content(schema = @Schema(implementation = AuthorResource.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ExceptionHandlerAdvice.class)))
    })
    @PostMapping()
    public ResponseEntity<?> add(@RequestBody AuthorResource authorResource) {
        authorResourceService.save(authorResource);
        return new ResponseEntity<>("Saved record", HttpStatus.OK);
    }

    @Operation(summary = "Delete author")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The author was removed", content = @Content(schema = @Schema(implementation = AuthorResource.class))),
            @ApiResponse(responseCode = "404", description = "author doesn't exits", content = @Content(schema = @Schema(implementation = ExceptionHandlerAdvice.class)))
    })
    @DeleteMapping("/{id}")
    public  void delete(@PathVariable String id){
        authorResourceService.delete(id);
    }

    @Operation(summary = "Update author")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Author updated successfully",content = @Content(schema = @Schema(implementation = AuthorResource.class))),
            @ApiResponse(responseCode = "404", description = "Author doesn't exits",content = @Content(schema = @Schema(implementation = ExceptionHandlerAdvice.class)))

    })
    @PutMapping("{id}")
    public  ResponseEntity<?> update(@RequestBody AuthorResource authorResource, @PathVariable String id){
        authorResourceService.update(authorResource, id);
        return  new ResponseEntity<>("Updated record", HttpStatus.OK);
    }



}
