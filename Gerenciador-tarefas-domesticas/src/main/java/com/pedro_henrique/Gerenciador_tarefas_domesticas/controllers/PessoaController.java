package com.pedro_henrique.Gerenciador_tarefas_domesticas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pedro_henrique.Gerenciador_tarefas_domesticas.DTOs.PessoaDTO;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.DTOs.PessoaResponseDTO;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.services.PessoaService;


@RestController
@RequestMapping(value = "/pessoas")
public class PessoaController {
    
    @Autowired
    PessoaService pessoaService;

    @PostMapping(value = "/pessoa")
    public void inserirPessoa(@RequestBody  PessoaDTO objetoBody) {
        pessoaService.inserirPessoa(objetoBody);
    }

    @GetMapping(value = "/{id}")
    public PessoaResponseDTO buscarResponsavel(@PathVariable Integer id) {
        return pessoaService.buscarResponsavel(id);
    }
}
