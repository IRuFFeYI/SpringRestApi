package com.spring.restApi.SpringRestApi.controller;

import com.spring.restApi.SpringRestApi.model.PhonebookEntry;
import com.spring.restApi.SpringRestApi.service.PhonebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.QueryAnnotation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/phonebook")
public class PhonebookController {

    @Autowired
    PhonebookService phonebookService;


    @GetMapping("/{id}")
    public PhonebookEntry getPhonebookEntryById(@PathVariable Integer id){
        return phonebookService.findById(id).get();
    }

    @GetMapping()
    public Iterable<PhonebookEntry> getPhonebookEntries(@RequestParam(required = false) String name, @RequestParam(name = "vorname", required = false) String prename){
        if(name == null && prename == null){
            return phonebookService.findAll();
        }
        else if(prename == null){
            return phonebookService.getAllByName(name);
        }
        else if(name == null){
            return phonebookService.getAllByPrename(prename);
        }
        return phonebookService.getAllByNameAndPrename(name, prename);
    }

    @PostMapping()
    public PhonebookEntry createPhonebookEntry(@RequestBody PhonebookEntry entry){
        return phonebookService.save(entry);
    }

    @PutMapping()
    public PhonebookEntry updatePhonebookEntry(@RequestBody PhonebookEntry entry){
        return phonebookService.save(entry);
    }

    @DeleteMapping()
    public void updatePhonebookEntry(@PathVariable Integer id){
        phonebookService.deleteById(id);
    }
}
