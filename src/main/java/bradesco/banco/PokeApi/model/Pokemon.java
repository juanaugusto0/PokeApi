package bradesco.banco.PokeApi.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class Pokemon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pokemonUniqueId;

    private int id;

    private String name;

    private int height;
    private int weight;

    @ManyToOne
    @JoinColumn(name = "pokedex_id")
    private Pokedex pokedex;

}
