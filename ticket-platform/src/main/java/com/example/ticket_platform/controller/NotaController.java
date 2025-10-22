package com.example.ticket_platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ticket_platform.model.Admin;
import com.example.ticket_platform.model.Nota;
import com.example.ticket_platform.model.Operatore;
import com.example.ticket_platform.model.security.DatabaseUserDetails;
import com.example.ticket_platform.repository.AdminRepository;
import com.example.ticket_platform.repository.NotaRepository;
import com.example.ticket_platform.repository.OperatoreRepository;

import jakarta.validation.Valid;




@Controller
@RequestMapping("/nota")
public class NotaController {

    @Autowired
    private NotaRepository repository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private OperatoreRepository operatoreRepository;

//     @GetMapping("/create")
//     public String createForm(Model model) {
//         model.addAttribute("nota", new Nota());
//         model.addAttribute("editMode", false); // puoi usarlo nel template per distinguere create/edit
//         return "/nota/edit"; // lo stesso template che usi per edit
// }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("nota") Nota nota, 
                        BindingResult bindingResult,
                        @AuthenticationPrincipal DatabaseUserDetails userDetails) {
        
        if(bindingResult.hasErrors()){
            return "offerte/create";
        }

        String ruolo= userDetails.getAuthorities().iterator().next().getAuthority();

        if(ruolo.equals("ADMIN")){
            Admin admin = adminRepository.findByEmail(userDetails.getUsername()).get();
            nota.setAdmin(admin);
        }else if(ruolo.equals("OPERATORE")){
            Operatore operatore=operatoreRepository.findByEmail(userDetails.getUsername()).get();
            nota.setOperatore(operatore);
        }
        repository.save(nota);

        return "redirect:/ticket/show/" +nota.getTicket().getId();
    }
    
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        Nota nota = repository.findById(id).get();
        model.addAttribute("editMode", true);
        model.addAttribute("nota", nota);
        return "/nota/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("nota") Nota nota,
                        BindingResult bindingResult, Model model) {
        
        if(bindingResult.hasErrors()){
             model.addAttribute("editMode", true);
             return "/nota/edit";
        }
        repository.save(nota);
        return "redirect:/ticket/show/"+nota.getTicket().getId();
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        
        Nota nota = repository.findById(id).get();
        repository.delete(nota);
        
        return "redirect:/ticket/show/"+nota.getTicket().getId();
    }
    

    
    

}
