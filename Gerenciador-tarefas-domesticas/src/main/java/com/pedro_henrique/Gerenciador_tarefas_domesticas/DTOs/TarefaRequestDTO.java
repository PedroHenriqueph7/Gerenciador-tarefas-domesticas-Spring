package com.pedro_henrique.Gerenciador_tarefas_domesticas.dtos;

import com.pedro_henrique.Gerenciador_tarefas_domesticas.entities.enums.PriorityTarefa;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.entities.enums.StatusTarefa;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    
    @NotNull(message = "O Campo de id da Categoria é obrigatório")
    private int category_id;

}
