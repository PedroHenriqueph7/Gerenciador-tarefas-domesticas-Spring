package com.pedro_henrique.Gerenciador_tarefas_domesticas.exceptions;

public class CategoriaJaExistenteNoBancoDados extends RuntimeException {
    public CategoriaJaExistenteNoBancoDados(String message) {
        super(message);
    }
}
