package com.example.ticket_platform.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ticket_platform.model.Nota;
import com.example.ticket_platform.model.Ticket;
import com.example.ticket_platform.repository.NotaRepository;
import com.example.ticket_platform.repository.TicketRepository;




@RestController
@CrossOrigin
@RequestMapping("/api/ticket")
public class TicketRestController {

    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    NotaRepository notaRepository;

    @GetMapping
    public List<Ticket> getMethodName(@RequestParam(name="keyword", required=false) String keyword ) {

        List<Ticket> result = null;
        if(keyword != null && !keyword.isBlank()){
            result=ticketRepository.findByTitoloContainingIgnoreCase(keyword);
        }else{
            result=ticketRepository.findAll();
        }


        return result;
    }

    @GetMapping("{id}")
    public Ticket get(@PathVariable("id") Integer id) {
        return ticketRepository.findById(id).get() ;
    }

    @PostMapping
    public Ticket create(@RequestBody Ticket ticket) {
        
        return ticketRepository.save(ticket);
    }
    
    @PutMapping("{id}")
    public Ticket put(@PathVariable ("id") Integer id, @RequestBody Ticket ticket) {
        
        
        return ticketRepository.save(ticket);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Integer id){
       
        Ticket ticket=ticketRepository.findById(id).get();

        List<Nota> note = ticket.getNote();

        notaRepository.deleteAll(note);

        ticketRepository.deleteById(id);
    }
    

}
