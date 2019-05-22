package com.spring.restApi.SpringRestApi.dataaccess;

import com.spring.restApi.SpringRestApi.model.PhonebookEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhonebookRepository extends JpaRepository<PhonebookEntry, Integer> {

    Iterable<PhonebookEntry> getAllByName(String name);
    Iterable<PhonebookEntry> getAllByPrename(String name);
    Iterable<PhonebookEntry> getAllByNameAndPrename(String name, String prename);
}
