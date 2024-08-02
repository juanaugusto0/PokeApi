package bradesco.banco.PokeApi.repository;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import bradesco.banco.PokeApi.model.Pokedex;
import bradesco.banco.PokeApi.model.Pokemon;
import bradesco.banco.PokeApi.service.PokemonService;

@DataJpaTest
public class PokemonRepositoryTest {

    
    @Autowired
    private TestEntityManager entityManager;

    @InjectMocks
    private PokemonService pokemonService;
    
    @Autowired
    @Mock
    private PokemonRepository pokemonRepository;
    
    @Mock
    HistoryRepository historyRepository;

    @BeforeEach
    public void setUp() {
        Pokedex pokedex = Mockito.mock(Pokedex.class);
        entityManager.persist(pokedex);
        Pokemon pokemon = pokemonService.getPokemonByName("pikachu");
        pokemon.setPokedex(pokedex);
        entityManager.persist(pokemon);
    }

    @Test
    @DisplayName("Procurar um pokemon correto utilizando findByName e retornar o pokemon")
    public void findByCorrectName() {
        Pokemon pokemon = pokemonRepository.findByName("pikachu");
        assertThat(pokemon.getName().equalsIgnoreCase("Pikachu"));
    }
    
    @Test
    @DisplayName("Procurar um pokemon incorreto utilizando findByName e retornar nulo")
    public void findByIncorrectName() {
        Pokemon pokemon = pokemonRepository.findByName("charmander");
        assertThat(pokemon).isNull();
    }

    @Test
    @DisplayName("Procurar um pokemon correto utilizando findByPokemon e retornar o pokemon")
    public void findByCorrectPokemon() {
        Pokemon pokemon = pokemonRepository.findByPokemon(25, 1L).get(0);
        assertThat(pokemon.getName().equalsIgnoreCase("Pikachu"));
    }

    @Test
    @DisplayName("Procurar um pokemon incorreto utilizando findByPokemon e retornar nulo")
    public void findByIncorrectPokemon() {
        List<Pokemon> pokemons = pokemonRepository.findByPokemon(1, 1L);
        assertThat(pokemons).isEmpty();
    }
}
