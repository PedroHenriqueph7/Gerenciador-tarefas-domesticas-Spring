package com.pedro_henrique.Gerenciador_tarefas_domesticas.exceptions;

public class PessoaNaoEncontradaException extends RuntimeException {
    
    public PessoaNaoEncontradaException(String message){
        super(message);
    }
}
