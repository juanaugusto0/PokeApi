package bradesco.banco.PokeApi.service;

import static org.junit.jupiter.api.Assertions.*;

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

import bradesco.banco.PokeApi.exception.TrainerNotFoundException;
import bradesco.banco.PokeApi.model.Pokedex;
import bradesco.banco.PokeApi.repository.PokedexRepository;
import bradesco.banco.PokeApi.repository.PokemonRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class GetPokedexByTrainerTest {
    @InjectMocks
    private PokemonService pokemonService;
    @Mock
    private PokedexRepository pokedexRepository;
    @Mock
    private PokemonRepository pokemonRepository;

    Pokedex pokedex = Mockito.mock(Pokedex.class);

    @Test
    @DisplayName("Procura por um treinador já salvo na pokedex, não deve dar erro")
    public void GetPokedexByTrainerWithPokemonsTest() {
        String trainer = "Juan";

        Optional<Pokedex> pokedexOptional = Optional.of(pokedex);
        BDDMockito.when(pokedexRepository.findByTrainerName(trainer)).thenReturn(pokedexOptional);

        assertTrue(pokemonService.getPokedexByTrainer(trainer).equals(pokedex.toString()));
    }

    @Test
    @DisplayName("Procura por um treinador que não está salvo na pokedex, deve lançar uma exceção TrainerNotFoundException")
    public void GetPokedexByTrainerNotSaved() {
        String trainer = "Juan";

        Optional<Pokedex> pokedexOptional = Optional.empty();
        BDDMockito.when(pokedexRepository.findByTrainerName(trainer)).thenReturn(pokedexOptional);

        assertThrows(TrainerNotFoundException.class, () -> {
            pokemonService.getPokedexByTrainer(trainer);
        });
    }

    @Test
    @DisplayName("Procura por um treinador nulo, deve lançar uma exceção NullPointerException")
    public void GetPokedexByNullTrainer() {
        assertThrows(NullPointerException.class, () -> {
            pokemonService.getPokedexByTrainer("");
        });
    }
    
}
