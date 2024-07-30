package bradesco.banco.PokeApi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class PokemonSpecies {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public String name;

    @ManyToOne
    @JoinColumn(name = "generation_id")
    private Generation generation;

    @Override
    public String toString() {
        return name;
    }
}
