package com.example.ticket_platform.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ticket_platform.model.Operatore;

public interface OperatoreRepository extends JpaRepository<Operatore, Integer> {

    List<Operatore> findByDisponibileTrue();

    Optional<Operatore> findByEmail(String email);
}
