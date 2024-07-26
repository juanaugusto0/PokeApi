package bradesco.banco.PokeApi.model;

import java.util.ArrayList;
import java.util.List;

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

    @ManyToMany
    private List<Pokemon> pokemons;

    public Pokedex() {
        pokemons = new ArrayList<>();
    }

}
