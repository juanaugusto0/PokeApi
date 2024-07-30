package bradesco.banco.PokeApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import bradesco.banco.PokeApi.model.Generation;

public interface GenerationRepository extends JpaRepository<Generation, Long> {
}
