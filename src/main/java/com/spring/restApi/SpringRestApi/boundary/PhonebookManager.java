package com.spring.restApi.SpringRestApi.boundary;

import com.spring.restApi.SpringRestApi.dataaccess.PhonebookRepository;
import com.spring.restApi.SpringRestApi.model.PhonebookEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.Optional;

@Service
@Transactional
public class PhonebookManager {

    @Autowired
    PhonebookRepository phonebookRepository;

    public Optional<PhonebookEntry> getPhonebookEntryById(Integer id){
        if(id <= 0){
            throw new ValidationException();
        }
        Optional<PhonebookEntry> entry = phonebookRepository.findById(id);
        return entry;
    }

    public PhonebookEntry createPhonebookEntry(@Valid PhonebookEntry entry){
        PhonebookEntry newEntry = phonebookRepository.save(entry);
        return newEntry;
    }

    public void updatePhonebookEntry(@Valid PhonebookEntry entry){
        if(!phonebookRepository.existsById(entry.getId())){
            throw new IllegalArgumentException();
        }
        else {
            phonebookRepository.save(entry);
        }
    }

    public void deletePhonebookEntry(Integer id){
        if(id <= 0){
            throw new ValidationException();
        }
        phonebookRepository.deleteById(id);
    }

    public Iterable<PhonebookEntry> getEntityByNameOrPrename(String name, String prename){
        if(name == null && prename == null){
            return phonebookRepository.findAll();
        }
        else if(prename == null){
            return phonebookRepository.getAllByName(name);
        }
        else if(name == null){
            return phonebookRepository.getAllByPrename(prename);
        }
        return phonebookRepository.getAllByNameAndPrename(name, prename);
    }
}
