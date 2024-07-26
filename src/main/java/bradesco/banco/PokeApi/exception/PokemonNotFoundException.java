package bradesco.banco.PokeApi.exception;

public class PokemonNotFoundException extends RuntimeException {

    public PokemonNotFoundException(String name) {
        super("Pokemon de nome: '" + name + "' não encontrado.");
    }

}
