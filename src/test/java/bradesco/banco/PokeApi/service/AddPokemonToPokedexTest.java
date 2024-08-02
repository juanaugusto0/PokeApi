package bradesco.banco.PokeApi.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import bradesco.banco.PokeApi.repository.HistoryRepository;
import bradesco.banco.PokeApi.repository.PokedexRepository;
import bradesco.banco.PokeApi.repository.PokemonRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AddPokemonToPokedexTest {
    @InjectMocks
    private PokemonService pokemonService;
    @Mock
    private HistoryRepository historyRepository;
    @Mock
    private PokedexRepository pokedexRepository;
    @Mock
    private PokemonRepository pokemonRepository;

    @Test
    @DisplayName ("Adiciona um pokemon de nome correto à pokedex, não deve dar erro")
    public void AddPokemonWithCorrectNameToPokedex() {
        String name = "bulbasaur";
        String trainer = "Juan";
        assertTrue(pokemonService.addPokemonToPokedex(name, trainer).equals(name + " adicionado à pokedex de " + trainer));
    }

    @Test
    @DisplayName ("Adiciona um pokemon de nome nulo à pokedex, deve lançar uma exceção NullPointerException")
    public void AddPokemonWithNullNameToPokedex() {
        assertThrows(NullPointerException.class, () -> {
            pokemonService.addPokemonToPokedex("", "Juan");
        });
    }

    @Test
    @DisplayName ("Adiciona um pokemon de nome correto e treinador nulo à pokedex, deve lançar uma exceção NullPointerException")
    public void AddPokemonWithCorrectNameAndNullTrainerToPokedex() {
        assertThrows(NullPointerException.class, () -> {
            pokemonService.addPokemonToPokedex("bulbasaur", "");
        });
    }
}
