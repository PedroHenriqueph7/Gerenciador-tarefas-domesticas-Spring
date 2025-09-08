package com.pedro_henrique.Gerenciador_tarefas_domesticas.infra;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class RestErrorMessage {
    
    private HttpStatus status;
    private String message;
}
