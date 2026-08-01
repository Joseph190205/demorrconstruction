package com.rrconstruction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rrconstruction.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact,Integer>{

}