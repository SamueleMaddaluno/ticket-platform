package com.example.ticket_platform.model.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.ticket_platform.model.Admin;
import com.example.ticket_platform.model.Operatore;
import com.example.ticket_platform.model.Ruolo;
import com.example.ticket_platform.model.User;

public class DatabaseUserDetails implements UserDetails{

    private String username;

    private String password;

    private Set<GrantedAuthority> authorities;

    public DatabaseUserDetails(User user) {
        if(user instanceof Admin admin){
             this.username = admin.getEmail();
            this.password = admin.getPassword();
            this.authorities = new HashSet<>();
            for(Ruolo ruolo : admin.getRuoli()) {
            SimpleGrantedAuthority sGA = new SimpleGrantedAuthority(ruolo.getNome());
            this.authorities.add(sGA);
            
            }
       
        }else if(user instanceof Operatore operatore){
             this.username = operatore.getEmail();
            this.password = operatore.getPassword();
            this.authorities = new HashSet<>();
            for(Ruolo ruolo : operatore.getRuoli()) {
            SimpleGrantedAuthority sGA = new SimpleGrantedAuthority(ruolo.getNome());
            this.authorities.add(sGA);            
            }
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
}
