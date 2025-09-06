package com.pedro_henrique.Gerenciador_tarefas_domesticas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedro_henrique.Gerenciador_tarefas_domesticas.DTOs.CategoriaResquestDTO;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.entities.CategoriaTarefa;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.repositories.CategoriaTarefaRepository;

import jakarta.transaction.Transactional;

@Service
public class CategoriaService {
    
    @Autowired
    CategoriaTarefaRepository categoriaTarefaRepository;

    @Transactional
    public void cadastrarCategoria(CategoriaResquestDTO objetoDto) {
        CategoriaTarefa categoriaTarefa = new CategoriaTarefa();
        categoriaTarefa.setCategory_name(objetoDto.getCategory_name());
        
        categoriaTarefaRepository.save(categoriaTarefa);
    }
}
