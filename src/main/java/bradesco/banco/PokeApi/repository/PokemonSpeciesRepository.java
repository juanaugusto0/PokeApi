package bradesco.banco.PokeApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import bradesco.banco.PokeApi.model.PokemonSpecies;

public interface PokemonSpeciesRepository extends JpaRepository<PokemonSpecies, Long> {
}
