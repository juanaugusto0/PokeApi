package bradesco.banco.PokeApi.exception;

public class PokemonNotOnPokedexException extends RuntimeException {
    
    public PokemonNotOnPokedexException(String name, String trainer) {
        super("Pokemon de nome: " + name + " não encontrado na pokedex do " + trainer);
    }
}
