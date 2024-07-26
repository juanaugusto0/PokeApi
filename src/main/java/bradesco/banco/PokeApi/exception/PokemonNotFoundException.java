package bradesco.banco.PokeApi.exception;

public class PokemonNotFoundException extends RuntimeException {

    public PokemonNotFoundException(Long id) {
        super("Pokemon de id: '" + id + "' não encontrado.");
    }

}
