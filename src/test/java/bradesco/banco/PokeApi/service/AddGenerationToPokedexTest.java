package bradesco.banco.PokeApi.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import bradesco.banco.PokeApi.exception.GenerationNotFoundException;
import bradesco.banco.PokeApi.repository.HistoryRepository;
import bradesco.banco.PokeApi.repository.PokedexRepository;
import bradesco.banco.PokeApi.repository.PokemonRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AddGenerationToPokedexTest {
    @InjectMocks
    PokemonService pokemonService;
    @Mock
    PokemonRepository pokemonRepository;
    @Mock
    PokedexRepository pokedexRepository;
    @Mock
    HistoryRepository historyRepository;

    @Test
    @DisplayName("Adiciona uma geração à pokedex corretamente, não deve dar erro")
    public void AddGenerationToPokedexCorrectlyTest() {

        assertTrue(pokemonService.addGenerationToPokedex(3L,"Juan").contains("adicionada"));
    }

    @Test
    @DisplayName("Id da geração é nulo, deve lançar uma exceção NullPointerException")
    public void GenerationIdIsNullTest() {
        assertThrows(NullPointerException.class, () -> {
            pokemonService.addGenerationToPokedex(null,"Juan");
        });
    }

    @Test
    @DisplayName("Id da geração está correto mas treinador é nulo, deve lançar uma exceção NullPointerException")
    public void GenerationIdIsCorrectButTrainerIsNullTest() {
        assertThrows(NullPointerException.class, () -> {
            pokemonService.addGenerationToPokedex(3L,null);
        });
    }

    @Test
    @DisplayName("Id da geração está incorreto, deve lançar uma exceção GenerationNotFoundException")
    public void GenerationIdIsIncorrectTest() {
        assertThrows(GenerationNotFoundException.class, () -> {
            pokemonService.addGenerationToPokedex(98271364L,"Juan");
        });
    }
}
