package bradesco.banco.PokeApi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PokemonActionRequestDto {

    private String name;
    private String action;
    private String trainerName;

}
