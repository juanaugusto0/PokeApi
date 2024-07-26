package bradesco.banco.PokeApi.repository;


import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import bradesco.banco.PokeApi.model.Pokedex;

public interface PokedexRepository extends JpaRepository<Pokedex, Long> {
    
    Optional<Pokedex> findByTrainerName(String trainer);

}
