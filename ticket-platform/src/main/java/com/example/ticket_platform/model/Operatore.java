package com.example.ticket_platform.model;

import java.util.List;

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
@Table(name="operatori")
public class Operatore extends User{

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

    @NotNull
    private Boolean disponibile;

    @OneToMany(mappedBy="operatore")
    private List<Nota> noteO;

    @OneToMany(mappedBy="operatore")
    private List<Ticket> ticketsOp;

    @ManyToMany(fetch=FetchType.EAGER)
    private List<Ruolo> ruoli;

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

    public Boolean getDisponibile() {
        return disponibile;
    }

    public void setDisponibile(Boolean disponibile) {
        this.disponibile = disponibile;
    }

    public List<Ticket> getTicketsOp() {
        return ticketsOp;
    }

    public void setTicketsOp(List<Ticket> ticketsOp) {
        this.ticketsOp = ticketsOp;
    }

    public List<Ruolo> getRuoli() {
        return ruoli;
    }

    public void setRuoli(List<Ruolo> ruoli) {
        this.ruoli = ruoli;
    }

    public List<Nota> getNoteO() {
        return noteO;
    }

    public void setNoteO(List<Nota> noteO) {
        this.noteO = noteO;
    }

    
    
    
}
