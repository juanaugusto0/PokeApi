package bradesco.banco.PokeApi.model;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pokemon {
    private Long id;
    private String name;
    private List<Type> types;
    private List<Move> moves;
    private List<Ability> abilities;
}
