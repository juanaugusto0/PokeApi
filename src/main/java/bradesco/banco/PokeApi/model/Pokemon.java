package bradesco.banco.PokeApi.model;

import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class Pokemon {
    @Id
    private Long id;
    private String name;

    private int height;
    private int weight;

    @ManyToMany(mappedBy = "pokemons", fetch = FetchType.LAZY)
    private List<Pokedex> pokedexes;

}
