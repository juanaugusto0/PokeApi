package bradesco.banco.PokeApi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PokemonActionRequestDto {

    private String name;
    private String action;
    private String trainerName;

}
