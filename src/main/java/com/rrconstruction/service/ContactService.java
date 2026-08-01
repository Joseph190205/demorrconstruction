package com.rrconstruction.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rrconstruction.entity.Contact;
import com.rrconstruction.repository.ContactRepository;

@Service
public class ContactService {

    @Autowired
    private ContactRepository repository;

    public void save(Contact contact) {
        repository.save(contact);
    }

    public List<Contact> getAll() {
        return repository.findAll();
    }

    public Contact getById(int id) {
        return repository.findById(id).orElse(null);
    }

    public void delete(int id) {
        repository.deleteById(id);
    }

}