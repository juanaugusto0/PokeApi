package bradesco.banco.PokeApi.exception;

public class TrainerNotFoundException extends RuntimeException {

    public TrainerNotFoundException(String trainer) {
        super("Treinador de nome: '" + trainer + "' não encontrado.");
    }

}