package com.pedro_henrique.Gerenciador_tarefas_domesticas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pedro_henrique.Gerenciador_tarefas_domesticas.DTOs.PessoaDTO;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.DTOs.PessoaResponseDTO;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.services.PessoaService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping(value = "/pessoas")
public class PessoaController {
    
    @Autowired
    PessoaService pessoaService;

    @PostMapping(value = "/pessoa")
    public ResponseEntity<String> inserirPessoa(@Valid @RequestBody  PessoaDTO objetoBody) {
        pessoaService.cadastrarPessoa(objetoBody);
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
