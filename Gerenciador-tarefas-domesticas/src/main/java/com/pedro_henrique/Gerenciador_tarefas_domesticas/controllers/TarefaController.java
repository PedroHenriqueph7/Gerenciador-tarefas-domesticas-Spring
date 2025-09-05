package com.pedro_henrique.Gerenciador_tarefas_domesticas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pedro_henrique.Gerenciador_tarefas_domesticas.DTOs.TarefaRequestDTO;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.services.TarefaService;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping(value = "/tarefas")
public class TarefaController {
    
    @Autowired
    TarefaService tarefaService;

    @PostMapping(value = "/tarefa")
    public void inserirPessoa(@RequestBody TarefaRequestDTO objetoDto) {
        tarefaService.cadastrarTarefa(objetoDto);
    }
    
}
