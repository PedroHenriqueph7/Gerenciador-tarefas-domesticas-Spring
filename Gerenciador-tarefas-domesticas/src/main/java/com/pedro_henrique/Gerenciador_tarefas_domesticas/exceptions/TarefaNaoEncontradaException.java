package com.pedro_henrique.Gerenciador_tarefas_domesticas.exceptions;

public class TarefaNaoEncontradaException extends RuntimeException {

    public TarefaNaoEncontradaException() {
        super("Tarefa não encontrada no Banco de Dados!");
    }

    public TarefaNaoEncontradaException(String message) {
        super(message);
    }
}
