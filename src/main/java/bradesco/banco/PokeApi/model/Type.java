package bradesco.banco.PokeApi.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Type {
    private TypeDetail type;

    @Getter
    @Setter
    private static class TypeDetail {
        private String name;
    }
}
