package bradesco.banco.PokeApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import bradesco.banco.PokeApi.model.Pokedex;

public interface PokedexRepository extends JpaRepository<Pokedex, Long> {

}
