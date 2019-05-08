package com.spring.restApi.SpringRestApi.controller;

import com.spring.restApi.SpringRestApi.model.PhonebookEntry;
import com.spring.restApi.SpringRestApi.service.PhonebookService;
import com.spring.restApi.SpringRestApi.util.FieldErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.annotation.QueryAnnotation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import sun.security.validator.ValidatorException;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v2/phonebook")
public class PhonebookControllerv2 {

    @Autowired
    PhonebookService phonebookService;

    @GetMapping("/{id}")
    public ResponseEntity<PhonebookEntry> getPhonebookEntryById(@PathVariable Integer id){
        if(id <= 0 || id == null)
        {
            return new ResponseEntity(HttpStatus.valueOf(400));
        }
        try
        {
            ResponseEntity response = new ResponseEntity(phonebookService.findById(id).get(), HttpStatus.valueOf(200));
            return response;
        }
        catch (NoSuchElementException e)
        {
            return new ResponseEntity(HttpStatus.valueOf(404));
        }
    }

    @PostMapping()
    public ResponseEntity<PhonebookEntry> createPhonebookEntry(@RequestBody PhonebookEntry entry) throws ValidatorException {
        if(entry.getId() == 0 && entry.getName() != null && entry.getPrename() != null && entry.getPhoneNumber() != null)
            return ResponseEntity.ok(phonebookService.save(entry));
        else {
            throw new ValidatorException("Entry cannot be created");
        }
    }

    @ExceptionHandler(ValidatorException.class)
    ResponseEntity<String> exeptionHandler(ValidatorException e){
        return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @PutMapping()
    public ResponseEntity<PhonebookEntry> updatePhonebookEntry(@RequestBody PhonebookEntry entry){
        return ResponseEntity.ok(phonebookService.save(entry));
    }

    @PostMapping("/valid")
    public ResponseEntity<PhonebookEntry> createPhonebookEntryJavaValidation(@Valid @RequestBody PhonebookEntry entry) throws ValidatorException {
            return ResponseEntity.ok(phonebookService.save(entry));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    List<FieldErrorMessage>ValidExcetionHandler(MethodArgumentNotValidException e){
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<FieldErrorMessage> fieldErrorMessages = fieldErrors.stream()
                .map(fieldError -> new FieldErrorMessage(fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());
        return  fieldErrorMessages;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> updatePhonebookEntry(@PathVariable Integer id){
        phonebookService.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.valueOf(204));
    }

}
