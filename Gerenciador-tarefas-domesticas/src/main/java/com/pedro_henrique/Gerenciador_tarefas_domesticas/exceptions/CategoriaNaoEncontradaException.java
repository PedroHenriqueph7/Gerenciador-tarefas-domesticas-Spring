package com.pedro_henrique.Gerenciador_tarefas_domesticas.exceptions;

public class CategoriaNaoEncontradaException extends RuntimeException{
    
    public CategoriaNaoEncontradaException() {
        super("Categoria não encontrada no Banco de Dados");
    }
    
    public CategoriaNaoEncontradaException(String message) {
        super(message);
    }
}
