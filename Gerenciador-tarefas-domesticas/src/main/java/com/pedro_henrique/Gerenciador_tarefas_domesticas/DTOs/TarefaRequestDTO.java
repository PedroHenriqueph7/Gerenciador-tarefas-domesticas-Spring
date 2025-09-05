package com.pedro_henrique.Gerenciador_tarefas_domesticas.DTOs;

import com.pedro_henrique.Gerenciador_tarefas_domesticas.entities.Enums.PriorityTarefa;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.entities.Enums.StatusTarefa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TarefaRequestDTO {
    
    private Integer id;
    private String task_name;
    private PriorityTarefa priorityTarefa;
    private StatusTarefa statusTarefa;
    private int responsible_id;
    private int category_id;

}
