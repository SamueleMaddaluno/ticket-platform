package com.example.ticket_platform.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="tickets")
public class Ticket {

    @Id
    @GeneratedValue (strategy=GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @NotBlank(message="il nome deve essere inserito")
    private String titolo;

    @NotNull
    @NotBlank(message="la descrizione è obbligatoria")
    private String descrizione;

    private enum Stato{
        da_fare,
        in_corso,
       completato
    }

    @NotNull
    @Enumerated(EnumType.STRING)
    private Stato stato = Stato.da_fare;

    @ManyToMany
    @JoinTable(
        name="tickets_categorie",
        joinColumns=@JoinColumn(name="ticket_id"),
        inverseJoinColumns=@JoinColumn(name="categoria_id")
    )
    private List<Categoria> categorie = new ArrayList<>();

    @OneToMany(mappedBy="ticket")
    private List<Nota> note;

    @ManyToOne
    @JoinColumn(name="operatore_id")
    @NotNull(message="obbligatorio selezionare un operatore, se disponibile")
    private Operatore operatore;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Stato getStato() {
        return stato;
    }

    public void setStato(Stato stato) {
        this.stato = stato;
    }

    public List<Categoria> getCategorie() {
        return categorie;
    }

    public void setCategorie(List<Categoria> categorie) {
        this.categorie = categorie;
    }

    public List<Nota> getNote() {
        return note;
    }

    public void setNote(List<Nota> note) {
        this.note = note;
    }

    public Operatore getOperatore() {
        return operatore;
    }

    public void setOperatore(Operatore operatore) {
        this.operatore = operatore;
    }


    public Ticket(){}
}
