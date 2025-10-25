package com.example.ticket_platform.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ticket_platform.model.Nota;
import com.example.ticket_platform.model.Ticket;
import com.example.ticket_platform.repository.NotaRepository;
import com.example.ticket_platform.repository.TicketRepository;

import jakarta.validation.Valid;



@RestController
@CrossOrigin
@RequestMapping("/api/nota")
public class NotaRestController {

    @Autowired
    NotaRepository notaRepository;
    @Autowired
    TicketRepository ticketRepository;
   
    @PostMapping("/{id}/create")
    public Nota nota(@Valid @RequestBody  Nota nota,
                    @PathVariable("id") Integer id) {
        
        Ticket ticket = ticketRepository.findById(id).get();

        nota.setTicket(ticket);

        return notaRepository.save(nota);
    }
    
     @PutMapping("/{id}")
    public Nota put(@PathVariable ("id") Integer id, @RequestBody Nota nota) {
        
        
        return notaRepository.save(nota);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id){
       
        notaRepository.deleteById(id);
    }
   
}
