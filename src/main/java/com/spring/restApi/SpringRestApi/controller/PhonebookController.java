package com.spring.restApi.SpringRestApi.controller;

import com.spring.restApi.SpringRestApi.boundary.PhonebookManager;
import com.spring.restApi.SpringRestApi.model.PhonebookEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.ValidationException;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/phonebookentrys")
public class PhonebookController {

    @Autowired
    PhonebookManager boundary;

    @GetMapping("/{id}")
    public ResponseEntity<PhonebookEntry> getPhonebookEntryById(@PathVariable int id){
        Optional<PhonebookEntry> entity = boundary.getPhonebookEntryById(id);
        return ResponseEntity.of(entity);
    }

    @PostMapping()
    public ResponseEntity<URI> createPhonebookEntry(@RequestBody PhonebookEntry entry){
        boundary.createPhonebookEntry(entry);
        return ResponseEntity.created(UriComponentsBuilder.fromPath("").build(entry.getId())).build();
    }

    @PutMapping()
    public ResponseEntity updatePhonebookEntry(@RequestBody PhonebookEntry entry){
        boundary.updatePhonebookEntry(entry);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePhonebookEntry(@PathVariable int id){
        boundary.deletePhonebookEntry(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping()
    public ResponseEntity<Iterable<PhonebookEntry>> filterPhonebookEntries(@RequestParam(required = false) String name, @RequestParam(name = "vorname", required = false) String prename){
        Iterable<PhonebookEntry> entries = boundary.getEntityByNameOrPrename(name, prename);
        return ResponseEntity.ok(entries);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ValidationException.class, IllegalArgumentException.class})
    String ValidationExeptionHandler(Exception e){
        return e.getMessage();
    }

//    @ExceptionHandler(IllegalArgumentException.class)
//    ResponseEntity<String> IllegalArgumentExceptionHandler(IllegalArgumentException e){
//        return ResponseEntity.notFound().build();
//    }

}
