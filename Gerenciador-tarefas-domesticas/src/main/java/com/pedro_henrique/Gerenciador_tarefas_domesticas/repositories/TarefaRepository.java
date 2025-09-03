package com.pedro_henrique.Gerenciador_tarefas_domesticas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pedro_henrique.Gerenciador_tarefas_domesticas.entities.Pessoa;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.entities.Tarefa;
import java.util.List;


public interface TarefaRepository extends JpaRepository<Tarefa, Integer> {
    
    // Podemos definir consultas no banco por meio dos atributos da classe modelo
    // Tipo do Retorno /  atributos da classe modelo
    //Tarefa findByTask_name(String task_name);

    // Consultas personalizadas por meio de Querys em SQL ou JPQL
    //@Query(value = "SELECT * FROM tb_pessoa WHERE name = :name", nativeQuery = true)
    //Pessoa buscarPorName(@Param("name") String name);
}
