package com.pedro_henrique.Gerenciador_tarefas_domesticas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedro_henrique.Gerenciador_tarefas_domesticas.DTOs.TarefaRequestDTO;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.entities.CategoriaTarefa;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.entities.Pessoa;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.entities.Tarefa;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.exceptions.CategoriaNaoEncontradaException;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.exceptions.PessoaNaoEncontradaException;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.repositories.CategoriaTarefaRepository;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.repositories.PessoaRepository;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.repositories.TarefaRepository;

import jakarta.transaction.Transactional;

@Service
public class TarefaService {
    
    @Autowired
    TarefaRepository tarefaRepository;

    @Autowired
    PessoaRepository pessoaRepository;

    @Autowired
    CategoriaTarefaRepository categoriaTarefaRepository;

    @Transactional
    public void cadastrarTarefa(TarefaRequestDTO dto){

        Pessoa pessoaResponsavel = pessoaRepository.findById(dto.getResponsible_id())
        .orElseThrow(() -> new PessoaNaoEncontradaException("Responsavel não encontrado")); 

        CategoriaTarefa categoriaTarefa = categoriaTarefaRepository.findById(dto.getCategory_id())
        .orElseThrow(() -> new CategoriaNaoEncontradaException("Categoria não encontrada!"));

        Tarefa tarefa = new Tarefa();

        tarefa.setId(dto.getId());
        tarefa.setTask_name(dto.getTask_name());
        tarefa.setPriorityTarefa(dto.getPriorityTarefa());
        tarefa.setStatusTarefa(dto.getStatusTarefa());
        tarefa.setResponsible(pessoaResponsavel);
        tarefa.setCategory(categoriaTarefa);
        
        tarefaRepository.save(tarefa);
    }

    @Transactional
    public void deletarTarefa(Integer id){
        Tarefa tarefa = tarefaRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Tarefa não encontrada no Banco de Dados!"));

        tarefaRepository.deleteById(tarefa.getId());
    }
}
