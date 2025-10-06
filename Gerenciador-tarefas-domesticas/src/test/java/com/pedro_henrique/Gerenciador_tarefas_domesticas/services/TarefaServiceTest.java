package com.pedro_henrique.Gerenciador_tarefas_domesticas.services;

import com.pedro_henrique.Gerenciador_tarefas_domesticas.dtos.TarefaUpdateStatusDTO;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.entities.CategoriaTarefa;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.entities.Pessoa;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.entities.Tarefa;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.entities.enums.PriorityTarefa;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.entities.enums.StatusTarefa;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.exceptions.TarefaNaoEncontradaException;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.repositories.TarefaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TarefaServiceTest {

    @InjectMocks
    TarefaService tarefaService;

    @Captor
    ArgumentCaptor<Integer> idValidoArgumentCaptor;

    @Captor
    ArgumentCaptor<Tarefa> tarefaArgumentCaptor;

    @Mock
    TarefaRepository tarefaRepository;

    @Nested
    class deleteTarefaPeloId {

        @Test
        @DisplayName("Deve deletar uma tarefa pelo id com sucesso")
        void deveDeletarTarefaPeloIdComSucesso() {

            //arrange
            Integer idValido = 1;
            Tarefa tarefaBanco = new Tarefa(1, "Limpar a casa", PriorityTarefa.ALTA, StatusTarefa.CONCLUIDA, new Pessoa(), new CategoriaTarefa());

            when(tarefaRepository.findById(idValido)).thenReturn(Optional.of(tarefaBanco));

            doNothing().when(tarefaRepository).deleteById(idValido);

            // act
            tarefaService.deletarTarefa(idValido);

            // assert
            InOrder inOrder = inOrder(tarefaRepository);

            inOrder.verify(tarefaRepository).findById(idValido);

            inOrder.verify(tarefaRepository, times(1)).deleteById(idValido);

        }

        @Test
        @DisplayName("Deve retorna uma exception quando a tarefa não for encontrada no metodo deletar a tarefa")
        void deveRetornaExceptionPessoaNaoEncontradaDeletarTarefa() {

            // arrange
            Integer idInvalido = 99;

            when(tarefaRepository.findById(idInvalido)).thenReturn(Optional.empty());

            // act & assert
            assertThrows(TarefaNaoEncontradaException.class, () -> tarefaService.deletarTarefa(idInvalido));
            verify(tarefaRepository, never()).deleteById(anyInt());
        }
    }

    @Nested
    class marcarTarefaComoConcluida {
        @Test
        @DisplayName("Marcar Tarefa como concluida com sucesso")
        void marcarTarefaComoConcluidaComSucesso() {

            //arrange
            Integer idValido = 1;

            TarefaUpdateStatusDTO tarefaUpdateStatusDTO = new TarefaUpdateStatusDTO(StatusTarefa.CONCLUIDA);

            // Crie o objeto como ele "existe" no banco, antes da alteração.
            Tarefa tarefaPendente = new Tarefa(1, "Descer com o lixo", PriorityTarefa.ALTA, StatusTarefa.PENDENTE, new Pessoa(), new CategoriaTarefa());

            when(tarefaRepository.findById(idValido)).thenReturn(Optional.of(tarefaPendente));

            //act
            tarefaService.marcarTarefaComoConcluida(idValido, tarefaUpdateStatusDTO);


            // assert
            verify(tarefaRepository).save(tarefaArgumentCaptor.capture());

            Tarefa tarefaConcluida = tarefaArgumentCaptor.getValue();

            assertNotNull(tarefaConcluida);
            assertEquals(StatusTarefa.CONCLUIDA, tarefaConcluida.getStatusTarefa());
            assertEquals(tarefaPendente.getId(), tarefaConcluida.getId());
            assertEquals(tarefaPendente.getTask_name(), tarefaConcluida.getTask_name());
        }

        @Test
        @DisplayName("Deve retornar uma exception de Tarefa não encontrada para atualização do status")
        void deveRetornarExceptionTarefaNaoEncontradaParaAtualizarStatus() {

            //arrange
            Integer idInvalido = 1;

            TarefaUpdateStatusDTO tarefaUpdateStatusDTO = new TarefaUpdateStatusDTO(StatusTarefa.CONCLUIDA);

            when(tarefaRepository.findById(idInvalido)).thenReturn(Optional.empty());

            // act & assert
            assertThrows(TarefaNaoEncontradaException.class, () -> tarefaService.marcarTarefaComoConcluida(idInvalido, tarefaUpdateStatusDTO));
            verify(tarefaRepository, never()).save(any());

        }
    }

}


