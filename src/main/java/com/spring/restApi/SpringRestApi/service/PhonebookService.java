package com.spring.restApi.SpringRestApi.service;

import com.spring.restApi.SpringRestApi.model.PhonebookEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhonebookService extends JpaRepository<PhonebookEntry, Integer> {

    Iterable<PhonebookEntry> getAllByName(String name);
    Iterable<PhonebookEntry> getAllByPrename(String name);
    Iterable<PhonebookEntry> getAllByNameAndPrename(String name, String prename);
}
