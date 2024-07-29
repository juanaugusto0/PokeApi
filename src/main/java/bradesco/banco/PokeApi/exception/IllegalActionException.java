package bradesco.banco.PokeApi.exception;

public class IllegalActionException extends RuntimeException {

    public IllegalActionException(String message) {
        super("A ação " + message + " não é permitida para este pokémon.");
    }
    
}
