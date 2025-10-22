package com.example.ticket_platform.model.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.ticket_platform.model.Admin;
import com.example.ticket_platform.model.Operatore;
import com.example.ticket_platform.repository.AdminRepository;
import com.example.ticket_platform.repository.OperatoreRepository;

@Service
public class DatabaseUserDetailsService implements UserDetailsService{

    @Autowired
    private OperatoreRepository operatoreRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Operatore> opOpt = operatoreRepository.findByEmail(email);
        Optional<Admin> adOpt = adminRepository.findByEmail(email);
        if(opOpt.isPresent() ) {
            return new DatabaseUserDetails(opOpt.get());
        } else if(adOpt.isPresent()){
            return new DatabaseUserDetails(adOpt.get());
        }
        else {
            throw new UsernameNotFoundException("Username not found");
        }
    }
}
