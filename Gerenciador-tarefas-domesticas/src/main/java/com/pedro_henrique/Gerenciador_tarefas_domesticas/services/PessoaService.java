package com.pedro_henrique.Gerenciador_tarefas_domesticas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pedro_henrique.Gerenciador_tarefas_domesticas.DTOs.PessoaDTO;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.entities.Pessoa;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.repositories.PessoaRepository;

@Service
public class PessoaService {
    
    @Autowired
    private PessoaRepository pessoaRepository;

    @Transactional
    public void inserirPessoa(PessoaDTO objeto) {
        Pessoa entity = Pessoa.fromDTO(objeto);
        pessoaRepository.save(entity);
    }
}
