package bradesco.banco.PokeApi.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Move {
    private MoveDetail move;

    @Getter
    @Setter
    private static class MoveDetail {
        private String name;
    }
}