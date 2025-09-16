package com.pedro_henrique.Gerenciador_tarefas_domesticas.dtos;

import com.pedro_henrique.Gerenciador_tarefas_domesticas.entities.Enums.PriorityTarefa;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.entities.Enums.StatusTarefa;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TarefaRequestDTO {
    
    private Integer id;
    
    @NotBlank(message = "O Campo nome é obrigatorio")
    private String task_name;

    private PriorityTarefa priorityTarefa;
    private StatusTarefa statusTarefa;
    private int responsible_id;
    private int category_id;

}
