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


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pokedex [id = ").append(id)
          .append(", Nome do treinador = ").append(trainerName)
          .append(", Pokemons na pokedex = [");

        for (Pokemon pokemon : pokemons) {
            sb.append(pokemon.getName()).append(", ");
        }

        if (!pokemons.isEmpty()) {
            sb.delete(sb.length() - 2, sb.length());
        }

        sb.append("]]");
        return sb.toString();
    }

}
