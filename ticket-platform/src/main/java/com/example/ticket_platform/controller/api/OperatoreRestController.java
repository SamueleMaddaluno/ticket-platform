package com.example.ticket_platform.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ticket_platform.model.Operatore;
import com.example.ticket_platform.repository.OperatoreRepository;


@RestController
@CrossOrigin
@RequestMapping("/api/operatore")
public class OperatoreRestController {

    @Autowired
    OperatoreRepository operatoreRepository;


    @PutMapping("/{id}")
    public Operatore put(@PathVariable("id") Integer id, @RequestBody Operatore operatore) {
        return operatoreRepository.save(operatore);
    }
}
