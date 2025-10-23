package com.example.ticket_platform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.ticket_platform.model.Operatore;
import com.example.ticket_platform.model.Ticket;
import com.example.ticket_platform.model.security.DatabaseUserDetails;
import com.example.ticket_platform.repository.OperatoreRepository;
import com.example.ticket_platform.repository.TicketRepository;



@Controller
@RequestMapping("/operatore")
public class OperatoreController {

    @Autowired
    private OperatoreRepository operatoreRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @GetMapping("/home")
    public String home(Model model, @AuthenticationPrincipal DatabaseUserDetails userDetails) {


        Operatore operatore= operatoreRepository.findByEmail(userDetails.getUsername()).get();

        List<Ticket> tickets =ticketRepository.findByOperatore(operatore);

        model.addAttribute("tickets", tickets);
        model.addAttribute("operatore", operatore);
        return "operatore/home" ;
    }

    @PostMapping("/disponibile")
    public String tastoDisponibilita(@AuthenticationPrincipal DatabaseUserDetails userDetails,
                    RedirectAttributes redirectAttributes) {

        Operatore operatore = operatoreRepository.findByEmail(userDetails.getUsername()).get();

        List<Ticket> tickets = ticketRepository.findByOperatore(operatore);

        if (tickets.isEmpty()) {
            operatore.setDisponibile(!operatore.getDisponibile());
            operatoreRepository.save(operatore);
        }else{
            redirectAttributes.addFlashAttribute("alertMessage","non puoi cambiare la disponibilità con ticket aperti");
        }

        return "redirect:/operatore/home";
    }

    @PostMapping("/stato/{id}")
    public String modificaStato(@PathVariable("id") Integer id,
                                @RequestParam("stato") String stato) {

            Ticket ticket= ticketRepository.findById(id).get();

             Ticket.Stato stati = Ticket.Stato.valueOf(stato);


            ticket.setStato(stati);

            ticketRepository.save(ticket);
        
        
        return "redirect:/operatore/home";
    }
    
    

}
