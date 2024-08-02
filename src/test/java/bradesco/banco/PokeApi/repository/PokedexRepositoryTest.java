package bradesco.banco.PokeApi.repository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import bradesco.banco.PokeApi.model.Pokedex;

@DataJpaTest
public class PokedexRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PokedexRepository pokedexRepository;

    @BeforeEach
    public void setUp () {
        Pokedex pokedex = new Pokedex();
        pokedex.setTrainerName("Juan");
        entityManager.persist(pokedex);
    }

    @Test
    @DisplayName("Procurar por um treinador correto e retornar o treinador")
    public void findByCorrectTrainerName() {
        Pokedex pokedex = pokedexRepository.findByTrainerName("Juan").get();
        assertThat(pokedex.getTrainerName()).isEqualTo("Juan");
    }

    @Test
    @DisplayName("Procurar por um treinador incorreto e retornar vazio")
    public void findByIncorrectTrainerName() {
        assertNull(pokedexRepository.findByTrainerName("Ash").orElse(null));
    }
    
}
