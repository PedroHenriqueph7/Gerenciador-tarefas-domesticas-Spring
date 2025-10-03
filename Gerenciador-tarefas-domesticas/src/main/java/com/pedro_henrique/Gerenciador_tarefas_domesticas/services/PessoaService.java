package com.pedro_henrique.Gerenciador_tarefas_domesticas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pedro_henrique.Gerenciador_tarefas_domesticas.dtos.PessoaRequestDTO;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.dtos.PessoaResponseDTO;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.entities.Pessoa;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.exceptions.PessoaNaoEncontradaException;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.repositories.PessoaRepository;

@Service
public class PessoaService {
    
    @Autowired
    private PessoaRepository pessoaRepository;

    @Transactional
    public Integer cadastrarPessoa(PessoaRequestDTO objeto) {
        Pessoa entity = new Pessoa(objeto.getId(), objeto.getName(), objeto.getAge());
       
        pessoaRepository.save(entity);

        return objeto.getId();
    }

    @Transactional
    public void deletarPessoa(Integer id) {

       Pessoa pessoa = pessoaRepository.findById(id)
       .orElseThrow(() -> new PessoaNaoEncontradaException());

       pessoaRepository.deleteById(pessoa.getId());
    }

    @Transactional(readOnly = true)
    public List<PessoaResponseDTO> buscarPessoas(){
        
        List<Pessoa> moradoresList = pessoaRepository.findAll();

        List<PessoaResponseDTO> objetosList = moradoresList.stream().map(x -> new PessoaResponseDTO(x.getId(), x.getName(), x.getAge())).toList();

        return objetosList;
    }

    public PessoaResponseDTO buscarPessoaPeloId(Integer id) {
        Pessoa entityPessoa = pessoaRepository.findById(id).orElseThrow(PessoaNaoEncontradaException::new);

        PessoaResponseDTO objetoDto = new PessoaResponseDTO(entityPessoa.getId(), entityPessoa.getName(), entityPessoa.getAge());

        return objetoDto;
    }
}
