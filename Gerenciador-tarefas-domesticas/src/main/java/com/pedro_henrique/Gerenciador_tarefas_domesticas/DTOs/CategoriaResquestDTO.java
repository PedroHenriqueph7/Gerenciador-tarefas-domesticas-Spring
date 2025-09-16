package com.pedro_henrique.Gerenciador_tarefas_domesticas.dtos;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaResquestDTO {
    
    private Integer id;
    
    @NotBlank(message = "O Campo nome é obrigatorio")
    private String category_name;

}
