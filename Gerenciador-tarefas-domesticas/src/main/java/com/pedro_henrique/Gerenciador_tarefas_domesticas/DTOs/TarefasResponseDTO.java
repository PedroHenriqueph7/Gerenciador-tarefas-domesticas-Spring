package com.pedro_henrique.Gerenciador_tarefas_domesticas.dtos;

import com.pedro_henrique.Gerenciador_tarefas_domesticas.entities.CategoriaTarefa;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.entities.Pessoa;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.entities.Enums.PriorityTarefa;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.entities.Enums.StatusTarefa;

public record TarefasResponseDTO(Integer id, String task_name, PriorityTarefa priorityTarefa, StatusTarefa status, Pessoa responsible, CategoriaTarefa category) {
    
}
