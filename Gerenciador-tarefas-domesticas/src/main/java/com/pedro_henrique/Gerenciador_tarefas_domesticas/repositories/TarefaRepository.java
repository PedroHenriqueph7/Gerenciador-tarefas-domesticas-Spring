package com.pedro_henrique.Gerenciador_tarefas_domesticas.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pedro_henrique.Gerenciador_tarefas_domesticas.dtos.QuantidadeTarefasConcluidasPorResponsavelResponseDTO;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.dtos.TarefasResponseDTO;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.entities.Tarefa;



public interface TarefaRepository extends JpaRepository<Tarefa, Integer> {
    
    // Podemos definir consultas no banco por meio dos atributos da classe modelo
    // Tipo do Retorno /  atributos da classe modelo
    //Tarefa findByTask_name(String task_name);

   // List<TarefasResponseDTO> findAllByOrderByPriorityTarefaAsc();

    // Consultas personalizadas por meio de Querys em SQL ou JPQL
    //@Query(value = "SELECT * FROM tb_pessoa WHERE name = :name", nativeQuery = true)
    //Pessoa buscarPorName(@Param("name") String name);

    @Query(value = """
            SELECT new com.pedro_henrique.Gerenciador_tarefas_domesticas.dtos.TarefasResponseDTO(
                t.id, 
                t.task_name, 
                t.priorityTarefa, 
                t.statusTarefa, 
                t.responsible, 
                t.category
            )
            FROM Tarefa t WHERE t.statusTarefa = 'CONCLUIDA'
    """)
    List<TarefasResponseDTO> tarefasConcluidas();

    @Query(value = """
            SELECT new com.pedro_henrique.Gerenciador_tarefas_domesticas.dtos.TarefasResponseDTO(
                t.id,
                t.task_name,
                t.priorityTarefa, 
                t.statusTarefa, 
                t.responsible, 
                t.category
            )
            FROM Tarefa t WHERE t.statusTarefa = 'PENDENTE'
    """)
    List<TarefasResponseDTO> tarefasPendentes();

   // No seu Repository
    @Query("""
        SELECT new com.pedro_henrique.Gerenciador_tarefas_domesticas.dtos.TarefasResponseDTO(
            t.id, 
            t.task_name, 
            t.priorityTarefa, 
            t.statusTarefa, 
            t.responsible, 
            t.category
        ) 
        FROM Tarefa t
        ORDER BY CASE 
            WHEN t.priorityTarefa = com.pedro_henrique.Gerenciador_tarefas_domesticas.entities.enums.PriorityTarefa.ALTA  THEN 1
            WHEN t.priorityTarefa = com.pedro_henrique.Gerenciador_tarefas_domesticas.entities.enums.PriorityTarefa.MEDIA THEN 2
            WHEN t.priorityTarefa = com.pedro_henrique.Gerenciador_tarefas_domesticas.entities.enums.PriorityTarefa.BAIXA THEN 3
            ELSE 4
        END
    """)
    List<TarefasResponseDTO> findAllOrderByTarefaPriority();

    @Query("""
            SELECT new com.pedro_henrique.Gerenciador_tarefas_domesticas.dtos.QuantidadeTarefasConcluidasPorResponsavelResponseDTO(
                t.responsible.name,
                COUNT(t)
            )
            FROM Tarefa t
            WHERE t.statusTarefa = com.pedro_henrique.Gerenciador_tarefas_domesticas.entities.enums.StatusTarefa.CONCLUIDA
            GROUP BY t.responsible.name
            """)
          List<QuantidadeTarefasConcluidasPorResponsavelResponseDTO> quantidadeTarefasConcluidaResponsavel();  
}
