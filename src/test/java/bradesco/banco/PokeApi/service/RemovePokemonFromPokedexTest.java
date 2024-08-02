package bradesco.banco.PokeApi.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import org.springframework.boot.test.context.SpringBootTest;

import bradesco.banco.PokeApi.exception.PokemonNotFoundException;
import bradesco.banco.PokeApi.model.Pokedex;
import bradesco.banco.PokeApi.model.Pokemon;
import bradesco.banco.PokeApi.repository.HistoryRepository;
import bradesco.banco.PokeApi.repository.PokedexRepository;
import bradesco.banco.PokeApi.repository.PokemonRepository;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class RemovePokemonFromPokedexTest {
    @InjectMocks
    private PokemonService pokemonService;
    @Mock
    private HistoryRepository historyRepository;
    @Mock
    private PokedexRepository pokedexRepository;
    @Mock
    private PokemonRepository pokemonRepository;

    Pokedex pokedex = Mockito.mock(Pokedex.class);

    @Test
    @DisplayName ("Remove um pokemon de nome correto da pokedex de um treinador com esse pokemon, não deve dar erro")
    public void RemovePokemonWithCorrectNameFromPokedex() {
        String name = "bulbasaur";
        String trainer = "Juan";

        Optional<Pokedex> pokedexOptional = Optional.of(pokedex);

        BDDMockito.given(pokedexRepository.findByTrainerName(trainer)).willReturn(pokedexOptional);


        List<Pokemon> temporaryList = new ArrayList<>(List.of(pokemonService.getPokemonByName(name)));
        BDDMockito.when(pokemonRepository.findByPokemon(1, 0L)).thenReturn(temporaryList);

        assertTrue(pokemonService.removePokemonFromPokedex(name, trainer).equals(name + " removido da pokedex de " + trainer));
    }

    @Test
    @DisplayName("Remove um pokemon de nome nulo da pokedex, deve lançar uma exceção NullPointerException")
    public void RemovePokemonWithNullNameFromPokedex() {
        assertThrows(NullPointerException.class, () -> {
            pokemonService.removePokemonFromPokedex("", "Juan");
        });
    }

    @Test
    @DisplayName("Remove um pokemon de nome correto e treinador nulo da pokedex, deve lançar uma exceção NullPointerException")
    public void RemovePokemonWithCorrectNameAndNullTrainerFromPokedex() {
        assertThrows(NullPointerException.class, () -> {
            pokemonService.removePokemonFromPokedex("bulbasaur", "");
        });
    }

    @Test
    @DisplayName("Remove um pokemon de nome correto da pokedex de um treinador sem esse pokemon, deve lançar uma exceção PokemonNotFoundException")
    public void RemovePokemonWithCorrectNameFromPokedexWithoutPokemon() {
        String name = "bulbasaur";
        String trainer = "Juan";

        Optional<Pokedex> pokedexOptional = Optional.of(pokedex);

        BDDMockito.given(pokedexRepository.findByTrainerName(trainer)).willReturn(pokedexOptional);

        List<Pokemon> temporaryList = new ArrayList<>();
        BDDMockito.when(pokemonRepository.findByPokemon(1, 0L)).thenReturn(temporaryList);
        assertThrows(PokemonNotFoundException.class, () -> {
            pokemonService.removePokemonFromPokedex(name, trainer);
        });
    }
    
}
