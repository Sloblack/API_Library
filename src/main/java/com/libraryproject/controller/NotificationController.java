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

import com.libraryproject.model.Notification;
import com.libraryproject.service.NotificationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("notifications")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@Tag (name ="notifications", description = "Provides methods for managing Notifications")
public class NotificationController {
    @Autowired
    private NotificationService service;

    @Operation(summary = "Get All Notifications")
    @ApiResponse(responseCode = "200", description = "Found Notifications", content = {
        @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Notification.class)))
    })
    @GetMapping
    public List<Notification> getAll(){
        return service.getAll();
    }

    @Operation(summary = "Get Notification By Id")
    @ApiResponse(responseCode = "200", description = "Found Notification", content = {
        @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Notification.class)))
    })
    @GetMapping("{idNotification}")
    public ResponseEntity<Notification> getById(@PathVariable Integer idNotification){
        Notification notification = service.getByIdNotification(idNotification);
        return new ResponseEntity<>(notification, HttpStatus.OK);
    }

    @Operation(summary = "Add Notification")
    @ApiResponse(responseCode = "200", description = "Add Notification", content = {
        @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Notification.class)))
    })
    @PostMapping
    public void registrar(@RequestBody Notification notification){
        service.save(notification);
    }

    @Operation(summary = "Update Notification")
    @ApiResponse(responseCode = "200", description = "Update Notification", content = {
        @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Notification.class)))
    })
    @PutMapping("/{idNotification}")
    public ResponseEntity<?> update(@RequestBody Notification notification, @PathVariable Integer idNotification){
        Notification auxNotification = service.getByIdNotification(idNotification);
        notification.setIdNotifications(auxNotification.getIdNotifications());
        service.save(notification);
        return new ResponseEntity<>("Updated record", HttpStatus.OK);
    }

    @Operation(summary = "Delete Notification")
    @ApiResponse(responseCode = "200", description = "Delete Notification", content = {
        @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Notification.class)))
    })
    @DeleteMapping("/{idNotification}")
    public ResponseEntity<?> delete(@PathVariable Integer idNotification){
        service.delete(idNotification);
        return new ResponseEntity<>("Updated record", HttpStatus.OK);
    }
}