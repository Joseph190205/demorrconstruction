package com.rrconstruction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rrconstruction.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin,Integer>{

    Admin findByUsernameAndPassword(String username,String password);

}