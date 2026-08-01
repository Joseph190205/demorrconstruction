package com.rrconstruction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rrconstruction.entity.Admin;
import com.rrconstruction.repository.AdminRepository;

@Service
public class AdminService {

    @Autowired
    private AdminRepository repository;

    public Admin login(String username,String password){

        return repository.findByUsernameAndPassword(username,password);

    }

}