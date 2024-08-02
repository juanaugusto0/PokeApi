package bradesco.banco.PokeApi.service;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;


import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;


import org.springframework.boot.test.context.SpringBootTest;

import bradesco.banco.PokeApi.exception.GenerationNotFoundException;
import bradesco.banco.PokeApi.model.Generation;




@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class GetGenerationByIdTest {
    @InjectMocks
    PokemonService pokemonService;
    
    @Test
    @DisplayName("Id da geração está correto, não deve dar erro")
    public void GenerationIdIsCorrectTest() {
        Generation generation = pokemonService.getGenerationById(1L);
        assertTrue(generation.getId() == 1);
    }

    @Test
    @DisplayName("Id da geração é nulo, deve lançar uma exceção NullPointerException")
    public void GenerationIdIsNullTest() {
        assertThrows(NullPointerException.class, () -> {
            pokemonService.getGenerationById(null);
        });
    }

    @Test
    @DisplayName("Id da geração está incorreto, deve lançar uma exceção GenerationNotFoundException")
    public void GenerationIdIsIncorrectTest() {
        assertThrows(GenerationNotFoundException.class, () -> {
            pokemonService.getGenerationById(25L);
        });
    }
    
}
