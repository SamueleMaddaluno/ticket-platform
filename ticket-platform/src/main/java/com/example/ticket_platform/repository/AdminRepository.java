package com.example.ticket_platform.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ticket_platform.model.Admin;



public interface AdminRepository extends JpaRepository<Admin, Integer>{

    public Optional<Admin> findByEmail(String email);

}
