package bradesco.banco.PokeApi.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Pokedex {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String trainerName;

    @OneToMany(mappedBy = "pokedex")
    private Set<Pokemon> pokemons;

    public Pokedex() {
        pokemons = new HashSet<>();
    }

}
