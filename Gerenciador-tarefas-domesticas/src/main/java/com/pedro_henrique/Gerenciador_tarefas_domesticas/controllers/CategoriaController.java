package com.pedro_henrique.Gerenciador_tarefas_domesticas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pedro_henrique.Gerenciador_tarefas_domesticas.DTOs.CategoriaResquestDTO;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.services.CategoriaService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(value = "/categorias")
public class CategoriaController {
    
    @Autowired
    CategoriaService categoriaService;

    @PostMapping(value = "/categoria")
    public void inserirCategoria(@RequestBody CategoriaResquestDTO objeto) {
        
        categoriaService.cadastrarCategoria(objeto);
 
    }
    
}
