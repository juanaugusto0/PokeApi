package bradesco.banco.PokeApi.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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

    @ManyToMany(mappedBy = "pokemons")
    private List<Pokedex> pokedexes;

}
