package com.example.ticket_platform.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.ticket_platform.model.Categoria;
import com.example.ticket_platform.model.Operatore;
import com.example.ticket_platform.model.Ticket;
import com.example.ticket_platform.repository.CategoriaRepository;
import com.example.ticket_platform.repository.OperatoreRepository;
import com.example.ticket_platform.repository.TicketRepository;

import jakarta.validation.Valid;






@Controller
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    private TicketRepository repository;
    @Autowired
    private OperatoreRepository operatoreRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public String index(Model model, @RequestParam(name="keyword", required=false) String keyword) {

        List<Ticket>tic = null;

        if(keyword==null || keyword.isBlank()){
            tic=repository.findAll();
        }else{
             tic=repository.findByTitoloContainingIgnoreCase(keyword);
        }
            model.addAttribute("ticket",tic);
          
        return "ticket/index";
    }

     @GetMapping("/show/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {

        Optional<Ticket> optionaleT = repository.findById(id);
        Ticket ticket = optionaleT.get();
        
        if(optionaleT.isPresent()){
            model.addAttribute("ticket", optionaleT.get());
            model.addAttribute("empty", false);
            model.addAttribute("categorie", ticket.getCategorie());
        }else{
            model.addAttribute("empty",true);
        }
        return "ticket/show";
    }




    @GetMapping("/create")
    public String create(Model model) {
        List<Operatore> operatori= operatoreRepository.findByDisponibileTrue();
        List<Categoria> categorie = categoriaRepository.findAll();
        if(operatori.isEmpty()){
            model.addAttribute("noOperatori", true);
            return "ticket/create";
        }
        model.addAttribute("ticket", new Ticket());
        model.addAttribute("operatori", operatori);
        model.addAttribute("categorie", categorie);
        return "ticket/create";
    }

    @PostMapping("/create")
    public String save(@Valid @ModelAttribute("ticket") Ticket formTicket,
                        BindingResult bindingResult, RedirectAttributes redirectAttributes,
                        Model model ) {
        List<Operatore> operatori= operatoreRepository.findByDisponibileTrue();
        List<Categoria> categorie = categoriaRepository.findAll();
        if(bindingResult.hasErrors()){
            model.addAttribute("operatori", operatori);
            model.addAttribute("categorie", categorie);
            return "ticket/create";
        }
    
        redirectAttributes.addFlashAttribute("successMessage", "TICKET CREATO CORRETTAMENTE");
        repository.save(formTicket);
        return "redirect:/ticket";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        // Ticket ticket=repository.findById(id).get();
        // for(OffertaSpeciale offertaCanc : pizza.getOfferte()){
        //     OffertaRepository.delete(offertaCanc);
        // }

        repository.deleteById(id);
        
        
        return "redirect:/ticket";
    
    }
    

}
