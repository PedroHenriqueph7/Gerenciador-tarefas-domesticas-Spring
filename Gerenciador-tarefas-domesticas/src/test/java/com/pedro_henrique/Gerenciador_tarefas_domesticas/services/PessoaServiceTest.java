package com.pedro_henrique.Gerenciador_tarefas_domesticas.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.pedro_henrique.Gerenciador_tarefas_domesticas.dtos.PessoaRequestDTO;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.dtos.PessoaResponseDTO;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.exceptions.PessoaNaoEncontradaException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pedro_henrique.Gerenciador_tarefas_domesticas.entities.Pessoa;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.repositories.PessoaRepository;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class) // nós estampos permitindo o Junit utilizar o mockito
class PessoaServiceTest {
   
   // Utilizaremos o padrão tripo whei
      // Todo teste nosso precisa passar por 3 passos
      // Caso comentarmos linhas de códigos em nossos métodos reais e mesmo assim o teste passar isso significa que o teste não esta seguindo seu papel
      // arrange -> Primeiro ele ira arrumar, organizar tudo o que precisa para o seu teste
      // act -> Depois ele ira a chamar o trecho que de fato iremos testar
      // assert -> Por fim iremos fazer todas as verificações se ele executor como tinha que executar se ele chamou todos os parametros necessários no momento do test

   @InjectMocks
   private PessoaService pessoaService;

   @Captor
   private ArgumentCaptor<Pessoa> pessoaArgumentCaptor;

   @Captor
   private ArgumentCaptor<Integer> idValidoArgumentCaptor;

   @Mock
   private PessoaRepository pessoaRepository;


   @Nested
   class cadastarPessoa {
      @Test
      @DisplayName("Inserindo uma Pessoa no Banco de Dados com sucesso")
      void criaPessoaComSucesso() {
         //arrange

         // O DTO que chega na sua service
         PessoaRequestDTO input = new PessoaRequestDTO(null, "PhTeste", 29);

         // O objeto que o mock do repository vai retornar quando 'save' for chamado
         Pessoa entity = new Pessoa(1, "PhTeste", 29);

         // Configurando o mock // utilizamos ArgumentCaptor para capturar as informações passadas no Return
         doReturn(entity).when(pessoaRepository).save(pessoaArgumentCaptor.capture());

         //act
         // Chame o método que você quer testar e armazene o resultado
          pessoaService.cadastrarPessoa(input);

         Pessoa pessoaCaptured = pessoaArgumentCaptor.getValue();

         assertEquals(input.getName(), pessoaCaptured.getName());
         assertEquals(input.getAge(), pessoaCaptured.getAge());
      }

      @Test
      @DisplayName("Devera lancar uma excecao quando algum erro acontecer")
      void deveLancarExcecaoQuandoAlgumErroAcontecer() {
         // arrange
         PessoaRequestDTO input = new PessoaRequestDTO(null, "PhtesteFalha",20);

         doThrow(NullPointerException.class).when(pessoaRepository).save(any(Pessoa.class));
         //act & assert
         assertThrows(NullPointerException.class, () ->pessoaService.cadastrarPessoa(input)) ;

      }
   }

   @Nested
   class deletarPessoa {

      @Test
      @DisplayName("lançar a exceção quando a pessoa não for encontrada")
      void lancarExcecaoPessoaNaoEncontrada() {

         // arrange
         Integer idInexistente = 99;

         when(pessoaRepository.findById(idInexistente)).thenReturn(Optional.empty());
         // act & assert
         assertThrows(PessoaNaoEncontradaException.class, () -> pessoaService.deletarPessoa(idInexistente));
      }

      @Test
      @DisplayName("Deletar uma pessoa com sucesso atraves do id")
      void deletarPessoaCOmSucesso() {

         // arrange
         Integer inputId = 10;
         Pessoa pessoaExistente = new Pessoa(inputId, "Phteste", 20);

         when(pessoaRepository.findById(inputId)).thenReturn(Optional.of(pessoaExistente));

         // Opcional, mas explícito: Diga ao mock para não fazer nada quando deleteById for chamado.
         // Mockito já faz isso por padrão para métodos void, mas isso torna a intenção clara.
         doNothing().when(pessoaRepository).deleteById(anyInt());

         //act

         pessoaService.deletarPessoa(inputId);

         // assert

         // 1. VERIFIQUE se o método deleteById do repositório foi chamado.
         // 2. CAPTURE o argumento que foi passado para ele.
        verify(pessoaRepository).deleteById(idValidoArgumentCaptor.capture());

        Integer idCapturado = idValidoArgumentCaptor.getValue();

        assertEquals(inputId, idCapturado);
      }
   }

   @Nested
   class getPessoaId {

      @Test
      @DisplayName("Deve chamar o repositório com o ID correto")
      void deveChamarRepositoryComIdCorreto() {
         // arrange
         Integer userId = 1;

         when(pessoaRepository.findById(anyInt())).thenReturn(Optional.of(new Pessoa()));
         // act

         Optional<Pessoa> output = pessoaRepository.findById(userId);
         // assert
         verify(pessoaRepository).findById(idValidoArgumentCaptor.capture());
         assertEquals(userId, idValidoArgumentCaptor.getValue());
      }

      @Test
      @DisplayName("Deve lançar exceção ao buscar pessoa com ID inexistente")
      void deveLancarExceptionAoBuscarPessoaComIdInexistente() {
         // arrange
         Integer idInexistente = 99;

         when(pessoaRepository.findById(idInexistente)).thenReturn(Optional.empty());

         // act & assert
         assertThrows(PessoaNaoEncontradaException.class, () -> pessoaService.buscarPessoaPeloId(idInexistente));

      }

      @Test
      @DisplayName(("Retorna uma pessoa com sucesso pelo seu id"))
      void deveRetornaPessoaComSucesso() {

         // arrange
         Integer idValido = 1;
         Pessoa entityBanco = new Pessoa(1, "Phteste", 29);

         when(pessoaRepository.findById(idValido)).thenReturn(Optional.of(entityBanco));

         // act
         PessoaResponseDTO outputDto = pessoaService.buscarPessoaPeloId(idValido);
         //assert
         assertNotNull(outputDto);
         assertEquals(idValido, outputDto.id());
         assertEquals(entityBanco.getName(), outputDto.name());
         assertEquals(entityBanco.getAge(), outputDto.age());

         // Verifique se a interação com o mock realmente aconteceu como esperado.
         verify(pessoaRepository, times(1)).findById(idValido);
      }
   }

   @Nested
   class buscarPessoas {

      @Test
      @DisplayName("Deve retornar uma lista de pessoas com sucesso")
      void deveRetornarListPessoaComSucesso() {

         //arrange
         List<Pessoa> pessoasBancoList = List.of(
                                                   new Pessoa(1, "name1", 29),
                                                   new Pessoa(2, "name2", 30),
                                                   new Pessoa(3, "name3", 32)
                                                );
         when(pessoaRepository.findAll()).thenReturn(pessoasBancoList);

         int pessoasBancoListSize = pessoasBancoList.size();
         // act
         List<PessoaResponseDTO> outputList = pessoaService.buscarPessoas();
         // assert

         assertNotNull(outputList);
         assertEquals(pessoasBancoListSize, outputList.size());

         Pessoa pessoaEntrada = pessoasBancoList.get(0);
         PessoaResponseDTO pessoaSaida = outputList.get(0);

         assertEquals(pessoaEntrada.getId(), pessoaSaida.id());
         assertEquals(pessoaEntrada.getName(), pessoaSaida.name());
         assertEquals(pessoaEntrada.getAge(), pessoaSaida.age());
      }

      // Este teste é importante pois garante que caso a lista estiver vazia no meu banco ele retornar uma lista vazia e não um null que quebraria meu contrato com API gerando uma exceção
      @Test
      @DisplayName("Deve retornar uma lista vazia")
      void deveRetornarUmaListaVazia() {

         // arrange
         when(pessoaRepository.findAll()).thenReturn(Collections.emptyList());

         // act
         List<PessoaResponseDTO> outputList = pessoaService.buscarPessoas();

         // assert
          assertNotNull(outputList, "A lista nunca deve ser nula, mesmo que vazia.");
          assertTrue(outputList.isEmpty(), "A lista deve estar vazia quando o repositório não retorna dados.");
      }
   }

}