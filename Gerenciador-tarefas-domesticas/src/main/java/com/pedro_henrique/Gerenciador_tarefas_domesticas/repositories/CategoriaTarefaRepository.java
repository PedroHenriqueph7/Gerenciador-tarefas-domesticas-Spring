package com.pedro_henrique.Gerenciador_tarefas_domesticas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pedro_henrique.Gerenciador_tarefas_domesticas.entities.CategoriaTarefa;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoriaTarefaRepository extends JpaRepository<CategoriaTarefa, Integer> {

    @Query("""
    SELECT COUNT(ct) > 0 
    FROM CategoriaTarefa ct 
    WHERE UPPER(FUNCTION('unaccent', ct.category_name)) = UPPER(FUNCTION('unaccent', :name))
""")
    boolean existsCategoria(@Param("name") String name);
}
