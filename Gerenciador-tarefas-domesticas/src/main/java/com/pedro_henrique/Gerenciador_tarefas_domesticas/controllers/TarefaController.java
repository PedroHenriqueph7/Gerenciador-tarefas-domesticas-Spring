package com.pedro_henrique.Gerenciador_tarefas_domesticas.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pedro_henrique.Gerenciador_tarefas_domesticas.dtos.TarefaRequestDTO;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.dtos.TarefasResponseDTO;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.services.TarefaService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping(value = "/tarefas")
public class TarefaController {
    
    
    private final TarefaService tarefaService;

    public TarefaController(TarefaService tarefaService){
        this.tarefaService = tarefaService;
    }

    @PostMapping(value = "/tarefa")
    public ResponseEntity<String> inserirTarefa(@Valid @RequestBody TarefaRequestDTO objetoDto) {
        tarefaService.cadastrarTarefa(objetoDto);
        return ResponseEntity.ok("Tarefa Registrada com sucesso");
    }
    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletarTarefa(@PathVariable Integer id) {
        tarefaService.deletarTarefa(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/concluidas")
    public List<TarefasResponseDTO> tarefasConcluida() {
        return tarefaService.tarefasConcluida();
    }

    @GetMapping(value = "/pendentes")
    public List<TarefasResponseDTO> tarefasPendentes() {
        return tarefaService.tarefasPendentes();
    }

    @GetMapping
    public List<TarefasResponseDTO> tarefasPorPrioriodade() {
        return tarefaService.tarefasPorPrioridade();
    }
}
