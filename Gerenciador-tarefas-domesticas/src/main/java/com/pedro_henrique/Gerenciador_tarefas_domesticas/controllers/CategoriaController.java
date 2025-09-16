package com.pedro_henrique.Gerenciador_tarefas_domesticas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pedro_henrique.Gerenciador_tarefas_domesticas.dtos.CategoriaResquestDTO;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.services.CategoriaService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(value = "/categorias")
public class CategoriaController {
    
    @Autowired
    CategoriaService categoriaService;

    @PostMapping(value = "/categoria")
    public ResponseEntity<String> inserirCategoria(@Valid @RequestBody CategoriaResquestDTO objeto) {
        
        categoriaService.cadastrarCategoria(objeto);
        return ResponseEntity.ok().body("Categoria Cadastrada");
    }
    
}
