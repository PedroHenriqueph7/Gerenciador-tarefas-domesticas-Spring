package com.pedro_henrique.Gerenciador_tarefas_domesticas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pedro_henrique.Gerenciador_tarefas_domesticas.entities.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
    
}
