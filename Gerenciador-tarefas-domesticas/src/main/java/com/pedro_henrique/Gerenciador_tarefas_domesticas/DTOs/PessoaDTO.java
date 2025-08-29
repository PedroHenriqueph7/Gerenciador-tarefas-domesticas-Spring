package com.pedro_henrique.Gerenciador_tarefas_domesticas.DTOs;

import com.pedro_henrique.Gerenciador_tarefas_domesticas.entities.Pessoa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PessoaDTO {
    
    private Integer id;
    private String name;
    private int age;

    public PessoaDTO(Pessoa entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.age = entity.getAge();
    }

}
