package bradesco.banco.PokeApi.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class History {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Long id;

    private String pokemonName;

    private LocalDateTime timestamp;
}
