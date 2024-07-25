package bradesco.banco.PokeApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import bradesco.banco.PokeApi.model.History;

public interface HistoryRepository extends JpaRepository<History, Long> {
    
}
