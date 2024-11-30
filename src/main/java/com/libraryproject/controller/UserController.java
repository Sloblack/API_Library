package com.libraryproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

import com.libraryproject.model.User;
import com.libraryproject.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("users")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@Tag (name ="users", description = "Provides methods for managing Users")
public class UserController {
    @Autowired
    private UserService service;

    @Operation(summary = "Get All Users")
    @ApiResponse(responseCode = "200", description = "Found Users", content = {
        @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = User.class)))
    })
    @GetMapping
    public List<User> getAll(){
        return service.getAll();
    }

    @Operation(summary = "Get User By Id")
    @ApiResponse(responseCode = "200", description = "Found User", content = {
        @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = User.class)))
    })
    @GetMapping("/{idUser}")
    public ResponseEntity<User> getById(@PathVariable Integer idUser){
        User user = service.getByIdUser(idUser);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Operation(summary = "Add User")
    @ApiResponse(responseCode = "200", description = "Add User", content = {
        @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = User.class)))
    })
    @PostMapping
    public void registrar(@RequestBody User user){
        service.save(user);
    }

    @Operation(summary = "Update User")
    @ApiResponse(responseCode = "200", description = "Update User", content = {
        @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = User.class)))
    })
    @PutMapping("/{idUser}")
    public ResponseEntity<?> update(@RequestBody User user, @PathVariable Integer idUser){
        User auxUser = service.getByIdUser(idUser);
        user.setIdUser(auxUser.getIdUser());
        service.save(user);
        return new ResponseEntity<>("Updated record", HttpStatus.OK);
    }
}