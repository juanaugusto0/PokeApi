package bradesco.banco.PokeApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import bradesco.banco.PokeApi.model.Pokemon;

public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
    Pokemon findByName(String name);
}
