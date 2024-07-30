package bradesco.banco.PokeApi.model;

import java.util.List;
import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Generation {
    @Id
    private Long id;

    @SerializedName("main_region")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "main_region_id", referencedColumnName = "id")
    private MainRegion mainRegion;

    @SerializedName("pokemon_species")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "generation_id")
    private List<PokemonSpecies> pokemonSpecies;

    public Generation() {
        pokemonSpecies = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Generation [id=" + id + ", mainRegion=" + mainRegion.toString() + ", pokemonSpecies="
                + pokemonSpecies.toString() + "]";
    }

}
