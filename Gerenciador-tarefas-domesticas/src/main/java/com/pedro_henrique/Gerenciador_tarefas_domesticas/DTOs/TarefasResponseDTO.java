package com.pedro_henrique.Gerenciador_tarefas_domesticas.dtos;

import com.pedro_henrique.Gerenciador_tarefas_domesticas.entities.CategoriaTarefa;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.entities.Pessoa;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.entities.enums.PriorityTarefa;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.entities.enums.StatusTarefa;

public record TarefasResponseDTO(Integer id, String task_name, PriorityTarefa priorityTarefa, StatusTarefa status, Pessoa responsible, CategoriaTarefa category) {
    
}
