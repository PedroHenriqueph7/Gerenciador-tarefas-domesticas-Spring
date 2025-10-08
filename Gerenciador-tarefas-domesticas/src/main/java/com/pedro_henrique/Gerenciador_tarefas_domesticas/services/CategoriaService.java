package com.pedro_henrique.Gerenciador_tarefas_domesticas.services;

import com.pedro_henrique.Gerenciador_tarefas_domesticas.exceptions.CategoriaJaExistenteNoBancoDados;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.exceptions.NomeNaoDeveSerInvalido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.pedro_henrique.Gerenciador_tarefas_domesticas.dtos.CategoriaResquestDTO;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.entities.CategoriaTarefa;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.repositories.CategoriaTarefaRepository;

import jakarta.transaction.Transactional;

@Service
public class CategoriaService {
    
    @Autowired
    CategoriaTarefaRepository categoriaTarefaRepository;

    @Transactional
    public void cadastrarCategoria(CategoriaResquestDTO objetoDto) {

        boolean nomeValido = true;

        if (objetoDto.getCategory_name() == null || objetoDto.getCategory_name().isBlank()) {
            nomeValido = false;
        }

        if (nomeValido) {

            CategoriaTarefa categoriaTarefa = new CategoriaTarefa();
            categoriaTarefa.setCategory_name(objetoDto.getCategory_name());

            boolean categoriaExist = categoriaTarefaRepository.existsCategoria(objetoDto.getCategory_name());

            if (categoriaExist) {
                throw new CategoriaJaExistenteNoBancoDados("Categoria já existe no banco de dados");
            } else {
                categoriaTarefaRepository.save(categoriaTarefa);
            }

        } else {
            throw  new NomeNaoDeveSerInvalido();
        }


    }
}
