package bradesco.banco.PokeApi.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import org.springframework.boot.test.context.SpringBootTest;

import bradesco.banco.PokeApi.dto.PokemonActionRequestDto;
import bradesco.banco.PokeApi.exception.IllegalActionException;
import bradesco.banco.PokeApi.exception.PokemonNotFoundException;
import bradesco.banco.PokeApi.model.Pokedex;
import bradesco.banco.PokeApi.model.Pokemon;
import bradesco.banco.PokeApi.repository.HistoryRepository;
import bradesco.banco.PokeApi.repository.PokedexRepository;
import bradesco.banco.PokeApi.repository.PokemonRepository;

@SpringBootTest
@ExtendWith (MockitoExtension.class)
public class FeedPokemonTest {
    @InjectMocks
    PokemonService pokemonService;
    @Mock
    HistoryRepository historyRepository;
    @Mock
    PokedexRepository pokedexRepository;
    @Mock
    PokemonRepository pokemonRepository;

    Pokemon pokemon = Mockito.mock(Pokemon.class);

    Pokedex pokedex = Mockito.mock(Pokedex.class);
    PokemonActionRequestDto requestDto = Mockito.mock(PokemonActionRequestDto.class);

    @Test
    @DisplayName ("Alimenta um pokemon de nome correto da pokedex de um treinador com esse pokemon, não deve dar erro")
    public void FeedPokemonWithCorrectNameFromPokedex() {


        Optional<Pokedex> pokedexOptional = Optional.of(pokedex);
        
        requestDto = new PokemonActionRequestDto("pikachu", "feed", "ash");

        BDDMockito.when(pokedexRepository.findByTrainerName(requestDto.getTrainerName())).thenReturn(pokedexOptional);

        List<Pokemon> temporaryList = new ArrayList<>();
        temporaryList.add(pokemon);

        BDDMockito.when(pokemonRepository.findByPokemon(25, 0L)).thenReturn(temporaryList);

        String response = pokemonService.feedPokemon(requestDto);
        assertTrue(response.contains("alimentado"));
    }

    @Test
    @DisplayName ("Alimenta um pokemon de nome correto da pokedex de um treinador sem esse pokemon, deve dar erro")
    public void FeedPokemonWithCorrectNameFromPokedexWithoutPokemon() {
        Optional<Pokedex> pokedexOptional = Optional.of(pokedex);
        
        requestDto = new PokemonActionRequestDto("pikachu", "feed", "ash");

        BDDMockito.when(pokedexRepository.findByTrainerName(requestDto.getTrainerName())).thenReturn(pokedexOptional);

        List<Pokemon> temporaryList = new ArrayList<>();

        BDDMockito.when(pokemonRepository.findByPokemon(25, 0L)).thenReturn(temporaryList);

        
        assertThrows(PokemonNotFoundException.class, () -> {
            pokemonService.feedPokemon(requestDto);
        });
    }

    @Test
    @DisplayName("Utiliza uma ação incorreta, deve lançar IllegalActionException")
    public void FeedPokemonWithIncorrectAction() {
        Optional<Pokedex> pokedexOptional = Optional.of(pokedex);
        
        requestDto = new PokemonActionRequestDto("pikachu", "wrongAction", "ash");

        BDDMockito.when(pokedexRepository.findByTrainerName(requestDto.getTrainerName())).thenReturn(pokedexOptional);

        List<Pokemon> temporaryList = new ArrayList<>();
        temporaryList.add(pokemon);

        BDDMockito.when(pokemonRepository.findByPokemon(25, 0L)).thenReturn(temporaryList);

        assertThrows(IllegalActionException.class, () -> {
            pokemonService.feedPokemon(requestDto);
        });
    }
}
