package com.pedro_henrique.Gerenciador_tarefas_domesticas.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pedro_henrique.Gerenciador_tarefas_domesticas.dtos.PessoaRequestDTO;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.dtos.PessoaResponseDTO;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.services.PessoaService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping(value = "/pessoas")
public class PessoaController {
    
    
    private final PessoaService pessoaService;
    
    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }
    
    @PostMapping(value = "/pessoa")
    public ResponseEntity<String> inserirPessoa(@Valid @RequestBody  PessoaRequestDTO objetoBody) {
        Integer returnID = pessoaService.cadastrarPessoa(objetoBody);
        return ResponseEntity.ok().body("Morador Cadastrado com sucesso");

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletarPessoa(@PathVariable Integer id) {
        pessoaService.deletarPessoa(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping()
    public List<PessoaResponseDTO> buscarPessoas() {
        return pessoaService.buscarPessoas();
    }
    
}
