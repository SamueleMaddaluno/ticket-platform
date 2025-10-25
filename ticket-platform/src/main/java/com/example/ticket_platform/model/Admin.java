package com.example.ticket_platform.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="admins")
public class Admin extends User{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

     @NotNull
    @NotBlank(message="email deve esssere inserita")
    @Column(unique=true)
    private String email;

    @NotNull
    @NotBlank(message="passord obbligatoria")
    private String password;

    @ManyToMany(fetch=FetchType.EAGER)
    private List<Ruolo> ruoli;

     @OneToMany(mappedBy="admin")
    @JsonBackReference
    private List<Nota> noteA;

     public Integer getId() {
         return id;
     }

     public void setId(Integer id) {
         this.id = id;
     }

     public String getEmail() {
         return email;
     }

     public void setEmail(String email) {
         this.email = email;
     }

     public String getPassword() {
         return password;
     }

     public void setPassword(String password) {
         this.password = password;
     }

     public List<Ruolo> getRuoli() {
         return ruoli;
     }

     public void setRuoli(List<Ruolo> ruoli) {
         this.ruoli = ruoli;
     }

     public List<Nota> getNoteA() {
         return noteA;
     }

     public void setNoteA(List<Nota> noteA) {
         this.noteA = noteA;
     }

     

    
}
