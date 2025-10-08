package com.pedro_henrique.Gerenciador_tarefas_domesticas.exceptions;

public class NomeNaoDeveSerInvalido extends RuntimeException {

    public NomeNaoDeveSerInvalido() { super("O nome não deve ser invalido");}
    public NomeNaoDeveSerInvalido(String message) {
        super(message);
    }
}
