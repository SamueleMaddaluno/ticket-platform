package com.example.ticket_platform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ticket_platform.model.Operatore;
import com.example.ticket_platform.model.Ticket;

public interface TicketRepository extends  JpaRepository<Ticket, Integer>{
    public List<Ticket> findByTitoloContainingIgnoreCase(String titolo);
    public List<Ticket> findByOperatore(Operatore operatore);
  

}
