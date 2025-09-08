package com.pedro_henrique.Gerenciador_tarefas_domesticas.infra;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.pedro_henrique.Gerenciador_tarefas_domesticas.exceptions.CategoriaNaoEncontradaException;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.exceptions.PessoaNaoEncontradaException;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.exceptions.TarefaNaoEncontradaException;

@ControllerAdvice //Captura as exception em um contexto global e retorna elas nos resultados das APIs
public class RestExceptionHandler extends ResponseEntityExceptionHandler{
    
    @ExceptionHandler(TarefaNaoEncontradaException.class) // Anotação que faz o spring chamar esse metodo caso seja lançada um exception desta class 
    private ResponseEntity<String> tarefaNaoEncontradaHandler(TarefaNaoEncontradaException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa não encontrada no Banco de Dados!");
    }

    @ExceptionHandler(PessoaNaoEncontradaException.class)
    private ResponseEntity<RestErrorMessage> pessoaNaoEncontradaHandler(PessoaNaoEncontradaException exception) {
        RestErrorMessage threatResponse  = new RestErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage() );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(threatResponse);
    }

    @ExceptionHandler(CategoriaNaoEncontradaException.class)
    private ResponseEntity<RestErrorMessage> categoriaNaoEncontradaHandler(CategoriaNaoEncontradaException exception) {
        RestErrorMessage threatResponse = new RestErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(threatResponse);
    }

}
