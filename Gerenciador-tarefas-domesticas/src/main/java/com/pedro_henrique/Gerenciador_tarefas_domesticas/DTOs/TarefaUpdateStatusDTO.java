package com.pedro_henrique.Gerenciador_tarefas_domesticas.dtos;

import com.pedro_henrique.Gerenciador_tarefas_domesticas.entities.enums.StatusTarefa;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TarefaUpdateStatusDTO {

    private StatusTarefa statusTarefa;
}
