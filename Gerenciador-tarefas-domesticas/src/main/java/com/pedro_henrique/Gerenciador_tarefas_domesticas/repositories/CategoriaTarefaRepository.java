package com.pedro_henrique.Gerenciador_tarefas_domesticas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pedro_henrique.Gerenciador_tarefas_domesticas.entities.CategoriaTarefa;

public interface CategoriaTarefaRepository extends JpaRepository<CategoriaTarefa, Integer> {
    
}
