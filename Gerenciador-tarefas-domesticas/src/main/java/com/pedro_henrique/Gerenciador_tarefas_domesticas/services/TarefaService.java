package com.pedro_henrique.Gerenciador_tarefas_domesticas.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pedro_henrique.Gerenciador_tarefas_domesticas.dtos.QuantidadeTarefasConcluidasPorResponsavelResponseDTO;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.dtos.TarefaRequestDTO;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.dtos.TarefasResponseDTO;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.entities.CategoriaTarefa;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.entities.Pessoa;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.entities.Tarefa;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.exceptions.CategoriaNaoEncontradaException;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.exceptions.PessoaNaoEncontradaException;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.exceptions.TarefaNaoEncontradaException;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.repositories.CategoriaTarefaRepository;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.repositories.PessoaRepository;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.repositories.TarefaRepository;

@Service
public class TarefaService {
    
    private final TarefaRepository tarefaRepository;
    private final PessoaRepository pessoaRepository;
    private final CategoriaTarefaRepository categoriaTarefaRepository;

    public TarefaService(TarefaRepository tarefaRepository, PessoaRepository pessoaRepository, CategoriaTarefaRepository categoriaTarefaRepository){
        this.tarefaRepository = tarefaRepository;
        this.pessoaRepository = pessoaRepository;
        this.categoriaTarefaRepository = categoriaTarefaRepository;
    }

    @Transactional
    public void cadastrarTarefa(TarefaRequestDTO dto){

        Pessoa pessoaResponsavel = pessoaRepository.findById(dto.getResponsible_id())
        .orElseThrow(() -> new PessoaNaoEncontradaException("Responsavel não encontrado")); 

        CategoriaTarefa categoriaTarefa = categoriaTarefaRepository.findById(dto.getCategory_id())
        .orElseThrow(() -> new CategoriaNaoEncontradaException());

        Tarefa tarefa = new Tarefa();

        tarefa.setId(dto.getId());
        tarefa.setTask_name(dto.getTask_name());
        tarefa.setPriorityTarefa(dto.getPriorityTarefa());
        tarefa.setStatusTarefa(dto.getStatusTarefa());
        tarefa.setResponsible(pessoaResponsavel);
        tarefa.setCategory(categoriaTarefa);
        
        tarefaRepository.save(tarefa);
    }

    @Transactional
    public void deletarTarefa(Integer id){
        Tarefa tarefa = tarefaRepository.findById(id)
        .orElseThrow(() -> new TarefaNaoEncontradaException());

        tarefaRepository.deleteById(tarefa.getId());
    }

    @Transactional(readOnly = true)
    public List<TarefasResponseDTO> tarefasConcluida() {

        List<TarefasResponseDTO> tarefasDTO = tarefaRepository.tarefasConcluidas();
        return tarefasDTO;
    }

    
    @Transactional(readOnly = true)
    public List<TarefasResponseDTO> tarefasPendentes() {

        List<TarefasResponseDTO> tarefasDTO = tarefaRepository.tarefasPendentes();
        return tarefasDTO;
    }

    @Transactional(readOnly = true)
    public List<TarefasResponseDTO> tarefasPorPrioridade() {

        return tarefaRepository.findAllOrderByTarefaPriority();
    }

    @Transactional
    public void associarUsuarioATarefa(Integer id, TarefaRequestDTO novoResponsavelDTO) {

        Tarefa tarefa = tarefaRepository.findById(id).orElseThrow(() -> new TarefaNaoEncontradaException());

        int idNovoUsuario = novoResponsavelDTO.getResponsible_id();
        Pessoa responsavel = pessoaRepository.findById(idNovoUsuario).orElseThrow(() -> new PessoaNaoEncontradaException());
        
        tarefa.setResponsible(responsavel);

        tarefaRepository.save(tarefa);
    }

    @Transactional
    public void marcarTarefaComoConcluida(Integer id, TarefaRequestDTO novoStatus) {

        Tarefa tarefa = tarefaRepository.findById(id).orElseThrow(() -> new TarefaNaoEncontradaException());

        tarefa.setStatusTarefa(novoStatus.getStatusTarefa());

        tarefaRepository.save(tarefa);
    }

    @Transactional
    public List<QuantidadeTarefasConcluidasPorResponsavelResponseDTO> quantidadeTarefasConcluidasPorResponsavel() {
        return tarefaRepository.quantidadeTarefasConcluidaResponsavel();
    }

}
