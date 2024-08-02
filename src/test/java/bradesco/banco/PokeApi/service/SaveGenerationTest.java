package bradesco.banco.PokeApi.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import bradesco.banco.PokeApi.exception.GenerationNotFoundException;
import bradesco.banco.PokeApi.model.Generation;
import bradesco.banco.PokeApi.repository.GenerationRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class SaveGenerationTest {
    @InjectMocks
    PokemonService pokemonService;
    @Mock
    GenerationRepository generationRepository;

    Generation generation = Mockito.mock(Generation.class);

    @Test
    @DisplayName("Salva uma geração corretamente, não deve dar erro")
    public void SaveGenerationCorrectlyTest() {
        pokemonService.saveGeneration(1L);
        assertTrue(pokemonService.saveGeneration(1L).contains("salva com sucesso"));
    }

    @Test
    @DisplayName("Id da geração é nulo, deve lançar uma exceção NullPointerException")
    public void GenerationIdIsNullTest() {
        assertThrows(NullPointerException.class, () -> {
            pokemonService.saveGeneration(null);
        });
    }

    @Test
    @DisplayName("Id da geração está incorreto, deve lançar uma exceção GenerationNotFoundException")
    public void GenerationIdIsIncorrectTest() {
        assertThrows(GenerationNotFoundException.class, () -> {
            pokemonService.saveGeneration(25L);
        });
    }
}
