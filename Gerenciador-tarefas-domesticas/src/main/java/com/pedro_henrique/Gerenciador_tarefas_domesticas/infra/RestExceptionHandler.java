package com.pedro_henrique.Gerenciador_tarefas_domesticas.infra;

import java.util.stream.Collectors;

import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.pedro_henrique.Gerenciador_tarefas_domesticas.exceptions.CategoriaNaoEncontradaException;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.exceptions.PessoaNaoEncontradaException;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.exceptions.TarefaNaoEncontradaException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(TarefaNaoEncontradaException.class)
    protected ResponseEntity<String> tarefaNaoEncontradaHandler(TarefaNaoEncontradaException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa não encontrada no Banco de Dados!");
    }

    @ExceptionHandler(PessoaNaoEncontradaException.class)
    protected ResponseEntity<RestErrorMessage> pessoaNaoEncontradaHandler(PessoaNaoEncontradaException exception) {
        RestErrorMessage treatResponse  = new RestErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(treatResponse);
    }

    @ExceptionHandler(CategoriaNaoEncontradaException.class)
    protected ResponseEntity<RestErrorMessage> categoriaNaoEncontradaHandler(CategoriaNaoEncontradaException exception) {
        RestErrorMessage treatResponse = new RestErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(treatResponse);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(@NonNull
            MethodArgumentNotValidException ex, @NonNull
            HttpHeaders headers, @NonNull
            HttpStatusCode status, @NonNull
            WebRequest request) {

        Map<String, String> errors = ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .collect(Collectors.toMap(
                FieldError::getField,
                FieldError::getDefaultMessage,
                (msg1, msg2) -> msg1 + "; " + msg2 // merge caso o mesmo campo tenha >1 erro
            ));

        return ResponseEntity.status(status).body(errors);
    }
}

