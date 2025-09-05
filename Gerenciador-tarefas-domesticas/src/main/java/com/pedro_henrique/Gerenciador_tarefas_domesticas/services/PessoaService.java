package com.pedro_henrique.Gerenciador_tarefas_domesticas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pedro_henrique.Gerenciador_tarefas_domesticas.DTOs.PessoaDTO;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.DTOs.PessoaResponseDTO;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.entities.Pessoa;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.repositories.PessoaRepository;

@Service
public class PessoaService {
    
    @Autowired
    private PessoaRepository pessoaRepository;

    @Transactional
    public void cadastrarPessoa(PessoaDTO objeto) {
        Pessoa entity = new Pessoa(objeto.getId(), objeto.getName(), objeto.getAge());
        pessoaRepository.save(entity);
    }

    @Transactional(readOnly = true)
    public PessoaResponseDTO buscarResponsavel(Integer id) {
        Pessoa  pessoa = pessoaRepository.findById(id).get();
        PessoaResponseDTO pessoaResponseDTO = new PessoaResponseDTO(pessoa.getId(), pessoa.getName(), pessoa.getAge());
        return pessoaResponseDTO;
    }
}
