package com.pedro_henrique.Gerenciador_tarefas_domesticas.DTOs;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PessoaDTO {
    
    private Integer id;
    @NotBlank(message = "O nome é obrigátorio")
    private String name;

    @Min(value = 10)
    private int age;

}
