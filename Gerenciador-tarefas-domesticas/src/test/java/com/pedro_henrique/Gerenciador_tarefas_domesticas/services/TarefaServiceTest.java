package com.pedro_henrique.Gerenciador_tarefas_domesticas.services;

import com.pedro_henrique.Gerenciador_tarefas_domesticas.dtos.TarefaRequestDTO;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.dtos.TarefaUpdateStatusDTO;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.entities.CategoriaTarefa;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.entities.Pessoa;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.entities.Tarefa;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.entities.enums.PriorityTarefa;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.entities.enums.StatusTarefa;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.exceptions.CategoriaNaoEncontradaException;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.exceptions.NomeNaoDeveSerInvalido;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.exceptions.PessoaNaoEncontradaException;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.exceptions.TarefaNaoEncontradaException;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.repositories.CategoriaTarefaRepository;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.repositories.PessoaRepository;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.repositories.TarefaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
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
    PessoaRepository pessoaRepository;

    @Mock
    CategoriaTarefaRepository categoriaTarefaRepository;

    @Mock
    TarefaRepository tarefaRepository;

    @Nested
    class cadastrarTarefa {

        @Test
        @DisplayName("deve realizar o cadastro de tarefa com sucesso")
        void cadastrarTarefaComSucesso() {

            // arrange
            TarefaRequestDTO tarefaInput = new TarefaRequestDTO(null, "Compras do Mes", PriorityTarefa.ALTA, StatusTarefa.CONCLUIDA, 1, 2);

            Pessoa responsavel = new Pessoa(tarefaInput.getResponsible_id(), "Nome", 32);
            CategoriaTarefa categoria = new CategoriaTarefa(tarefaInput.getCategory_id(), "NomeCategoria", null);

            Tarefa tarefaParaSalvarBanco = new Tarefa(null, "Compras do Mes", PriorityTarefa.ALTA, StatusTarefa.CONCLUIDA, responsavel, categoria);


            when(pessoaRepository.findById(tarefaInput.getResponsible_id())).thenReturn(Optional.of(responsavel));

            when(categoriaTarefaRepository.findById(tarefaInput.getCategory_id())).thenReturn(Optional.of(categoria));

            when(tarefaRepository.save(tarefaParaSalvarBanco)).thenReturn(any(Tarefa.class));

            // act
            tarefaService.cadastrarTarefa(tarefaInput);
            // assert

            verify(tarefaRepository, times(1)).save(tarefaArgumentCaptor.capture());

            Tarefa tarefaPassadaParaSalvar = tarefaArgumentCaptor.getValue();

            assertNull(tarefaPassadaParaSalvar.getId());
            assertEquals(tarefaInput.getTask_name(), tarefaPassadaParaSalvar.getTask_name());
            assertEquals(tarefaInput.getResponsible_id(), tarefaPassadaParaSalvar.getResponsible().getId());
            assertEquals(tarefaInput.getCategory_id(), tarefaPassadaParaSalvar.getCategory().getId());


        }

        @Test
        @DisplayName("Deve lancar a exception de responsavel nao encontrado")
        void deveLancarExceptionResponsavelNaoEncontrado() {
            // arrange
            TarefaRequestDTO tarefaInput = new TarefaRequestDTO(null, "Compras do Mes", PriorityTarefa.ALTA, StatusTarefa.CONCLUIDA, 1, 2);

            when(pessoaRepository.findById(tarefaInput.getResponsible_id())).thenReturn(Optional.empty());

            // act & assert

            assertThrows(PessoaNaoEncontradaException.class, () -> tarefaService.cadastrarTarefa(tarefaInput));
            verify(tarefaRepository, never()).save(any(Tarefa.class));

        }

        @Test
        @DisplayName("Deve lancar a exception de categoria nao encontrada")
        void deveLancarExceptionCategoriaNaoEncontrado() {

            // arrange
            TarefaRequestDTO tarefaInput = new TarefaRequestDTO(null, "TarefaTeste", PriorityTarefa.ALTA, StatusTarefa.PENDENTE, 1,20);

            Pessoa responsavel = new Pessoa(1, "PessoaTeste", 29);
            when(pessoaRepository.findById(tarefaInput.getResponsible_id())).thenReturn(Optional.of(responsavel));
            when(categoriaTarefaRepository.findById(tarefaInput.getCategory_id())).thenReturn(Optional.empty());
            // act & assert

            assertThrows(CategoriaNaoEncontradaException.class, () -> tarefaService.cadastrarTarefa(tarefaInput));
            verify(tarefaRepository, never()).save(any(Tarefa.class));
        }


        @DisplayName("deve lancar uma exception quando o nome for invalido")
        @ParameterizedTest
        @NullAndEmptySource
        @ValueSource(strings = {" ", "\t", "\n"})
        void deveLancarExceptionNomeInvalido(String nomeInvalido) {

            // arrange
            TarefaRequestDTO tarefaInputComNomeInvalido = new TarefaRequestDTO(null, nomeInvalido, PriorityTarefa.ALTA, StatusTarefa.PENDENTE, 1, 4);

            // act & assert

            assertThrows(NomeNaoDeveSerInvalido.class, () -> tarefaService.cadastrarTarefa(tarefaInputComNomeInvalido));
        }
    }

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


