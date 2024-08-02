package bradesco.banco.PokeApi.service;


import org.springframework.boot.test.context.SpringBootTest;


import bradesco.banco.PokeApi.exception.PokemonNotFoundException;
import bradesco.banco.PokeApi.model.Pokemon;
import bradesco.banco.PokeApi.repository.HistoryRepository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class GetPokemonByNameTest {

    @InjectMocks
    PokemonService pokemonService;    
    @Mock
    private HistoryRepository historyRepository;
    
    @Test
    @DisplayName ("Nome do pokemon está correto, não deve dar erro")
    public void PokemonNameIsCorrectTest() {
        Pokemon pokemon = pokemonService.getPokemonByName("bulbasaur");
        assertTrue(pokemon.getId() == 1 );
    }

    @Test
    @DisplayName ("Nome do pokemon é nulo, deve lançar uma exceção NullPointerException")
    public void PokemonNameIsNullTest() {
        assertThrows(NullPointerException.class, () -> {
            pokemonService.getPokemonByName("");
        });
    }

    @Test
    @DisplayName ("Nome do pokemon está incorreto, deve lançar uma exceção PokemonNotFoundException")
    public void PokemonNameIsIncorrectTest() {
        assertThrows(PokemonNotFoundException.class, () -> {
            pokemonService.getPokemonByName("pikachuu");
        });
    }

    @Test
    @DisplayName ("Nome do pokemon está correto mas tem letras maiúsculas e espaços em volta, não deve dar erro")
    public void PokemonNameIsCorrectWithUpperCaseAndSpacesTest() {
        Pokemon pokemon = pokemonService.getPokemonByName("  BulbAsAUr  ");
        assertTrue(pokemon.getId() == 1 );
    }

    @Test
    @DisplayName ("Nome do pokemon tem caracteres especiais, deve lançar uma exceção PokemonNotFoundException")
    public void PokemonNameHasSpecialCharactersTest() {
        PokemonService pokemonService = new PokemonService();
        assertThrows(PokemonNotFoundException.class, () -> {
            pokemonService.getPokemonByName("bulbasaur@");
        });
    }

    
}
