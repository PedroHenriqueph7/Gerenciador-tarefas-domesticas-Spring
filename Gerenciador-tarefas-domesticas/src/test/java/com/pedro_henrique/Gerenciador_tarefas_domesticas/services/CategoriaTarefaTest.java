package com.pedro_henrique.Gerenciador_tarefas_domesticas.services;

import com.pedro_henrique.Gerenciador_tarefas_domesticas.dtos.CategoriaResquestDTO;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.entities.CategoriaTarefa;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.exceptions.CategoriaJaExistenteNoBancoDados;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.exceptions.NomeNaoDeveSerInvalido;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.repositories.CategoriaTarefaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoriaTarefaTest {

    @InjectMocks
    private CategoriaService categoriaService;

    @Mock
    private CategoriaTarefaRepository categoriaTarefaRepository;

    @Captor
    ArgumentCaptor<CategoriaTarefa> categoriaArgumentCaptor;

    @Nested
    class cadastrarCategoria {

        @Test
        @DisplayName("Cadastar categoria com sucesso")
        void cadastrarCategoriaComSucesso() {

            // arrange
            CategoriaResquestDTO categoriaInput = new CategoriaResquestDTO(null,"Deveres");

            CategoriaTarefa categoriaTarefaBanco = new CategoriaTarefa(1, "Domesticas", null);

            
            when(categoriaTarefaRepository.existsCategoria(categoriaInput.getCategory_name())).thenReturn(false);

            when(categoriaTarefaRepository.save(any(CategoriaTarefa.class))).thenReturn(categoriaTarefaBanco);
            // act
            categoriaService.cadastrarCategoria(categoriaInput);
            // assert

            verify(categoriaTarefaRepository, times(1)).save(categoriaArgumentCaptor.capture());
            CategoriaTarefa categoriaParaSalvar = categoriaArgumentCaptor.getValue();

            assertNull(categoriaParaSalvar.getId());
            assertEquals(categoriaInput.getCategory_name(), categoriaParaSalvar.getCategory_name());
        }

        @Test
        @DisplayName("Deverá Lançar exception Categoria ja existente no Banco de Dados")
        void deveLancarExceptionCategoriaJaExistenteBancoDados() {

            // arrange
            CategoriaResquestDTO categoriaInput = new CategoriaResquestDTO(null, "Domésticas");

            when(categoriaTarefaRepository.existsCategoria(categoriaInput.getCategory_name())).thenReturn(true);

            // act & assert

            assertThrows(CategoriaJaExistenteNoBancoDados.class, () -> categoriaService.cadastrarCategoria(categoriaInput));
            verify(categoriaTarefaRepository, never()).save(any(CategoriaTarefa.class));
        }


        @DisplayName("Devera lancar uma exception caso o nome passado for invalido")
        @ParameterizedTest
        @NullAndEmptySource
        @ValueSource(strings = {" ", "\t", "\n"})
        void deveLancarExceptionNomeInvalido(String nomeInvalido) {
            // arrange
            CategoriaResquestDTO categoriaInputComNomeInvalido = new CategoriaResquestDTO(null, nomeInvalido);

            // act & assert
            assertThrows(NomeNaoDeveSerInvalido.class, () ->categoriaService.cadastrarCategoria(categoriaInputComNomeInvalido));
        }
    }
}
