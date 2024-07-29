package bradesco.banco.PokeApi.exception;

public class GenerationNotFoundException extends RuntimeException {
    public GenerationNotFoundException(Long id) {
        super("Geração '" + id + "'' não encontrada.");
    }
    
}
