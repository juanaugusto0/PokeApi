package bradesco.banco.PokeApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import bradesco.banco.PokeApi.model.MainRegion;

public interface MainRegionRepository extends JpaRepository<MainRegion, Long> {
    
}
