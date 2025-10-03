package com.pedro_henrique.Gerenciador_tarefas_domesticas.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test-unitarios")
public class TarefaRepositoryTest {
    
    @Test
    void tarefasPendentes() {

    }
}
