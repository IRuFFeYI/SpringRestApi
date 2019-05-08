package com.spring.restApi.SpringRestApi.controller;

import com.spring.restApi.SpringRestApi.model.PhonebookEntry;
import com.spring.restApi.SpringRestApi.service.PhonebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.QueryAnnotation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/phonebook")
public class PhonebookController {

    @Autowired
    PhonebookService phonebookService;




    @GetMapping()
    public ResponseEntity<Iterable<PhonebookEntry>> getPhonebookEntries(@RequestParam(required = false) String name, @RequestParam(name = "vorname", required = false) String prename){
        if(name == null && prename == null){
            return ResponseEntity.ok(phonebookService.findAll());
        }
        else if(prename == null){
            return ResponseEntity.ok(phonebookService.getAllByName(name));
        }
        else if(name == null){
            return ResponseEntity.ok(phonebookService.getAllByPrename(prename));
        }
        return ResponseEntity.ok(phonebookService.getAllByNameAndPrename(name, prename));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhonebookEntry> getPhonebookEntryById(@PathVariable Integer id){
        ResponseEntity response = new ResponseEntity(phonebookService.findById(id).get(), HttpStatus.valueOf(200));
        return response;
    }

    @PostMapping()
    public ResponseEntity<PhonebookEntry> createPhonebookEntry(@RequestBody PhonebookEntry entry){
        return ResponseEntity.ok(phonebookService.save(entry));
    }

    @PutMapping()
    public ResponseEntity<PhonebookEntry> updatePhonebookEntry(@RequestBody PhonebookEntry entry){
        return ResponseEntity.ok(phonebookService.save(entry));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> updatePhonebookEntry(@PathVariable Integer id){
        phonebookService.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.valueOf(204));
    }
}
