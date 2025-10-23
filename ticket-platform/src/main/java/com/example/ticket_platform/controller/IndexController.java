package com.example.ticket_platform.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ticket_platform.model.security.DatabaseUserDetails;


@Controller
@RequestMapping("/")
public class IndexController {

     @GetMapping("/")
    public String login(@AuthenticationPrincipal DatabaseUserDetails userDetails) {

      if(userDetails == null){
          return "redirect:/login";
       }

       String ruolo= userDetails.getAuthorities().iterator().next().getAuthority();

  

       if (ruolo.equals("ADMIN")){
        return "redirect:/ticket";
       }
       else if(ruolo.equals("OPERATORE")){
        return "redirect:/operatore/home";
       }
       else{
        return "redirect:/login";
       }
    }
    
    

}
