package bradesco.banco.PokeApi.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ability {
    private AbilityDetail ability;

    @Getter
    @Setter
    private static class AbilityDetail {
        private String name;
    }
}