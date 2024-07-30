package bradesco.banco.PokeApi.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

import bradesco.banco.PokeApi.dto.PokemonActionRequestDto;
import bradesco.banco.PokeApi.exception.GenerationNotFoundException;
import bradesco.banco.PokeApi.exception.IllegalActionException;
import bradesco.banco.PokeApi.exception.PokemonNotFoundException;
import bradesco.banco.PokeApi.exception.TrainerNotFoundException;
import bradesco.banco.PokeApi.model.Generation;
import bradesco.banco.PokeApi.model.History;
import bradesco.banco.PokeApi.model.Pokedex;
import bradesco.banco.PokeApi.model.Pokemon;
import bradesco.banco.PokeApi.model.PokemonSpecies;
import bradesco.banco.PokeApi.repository.GenerationRepository;
import bradesco.banco.PokeApi.repository.HistoryRepository;
import bradesco.banco.PokeApi.repository.PokedexRepository;
import bradesco.banco.PokeApi.repository.PokemonRepository;

@Service
public class PokemonService {
    private final RestTemplate restTemplate;
    private final String url = "https://pokeapi.co/api/v2/";
    @Autowired
    private HistoryRepository historyRepository;
    @Autowired
    private PokedexRepository pokedexRepository;
    @Autowired
    private PokemonRepository pokemonRepository;
    @Autowired
    private GenerationRepository generationRepository;

    @Autowired
    private Gson gson;

    public PokemonService() {
        this.restTemplate = new RestTemplate();
        this.gson = new Gson();
    }

    public Pokemon getPokemonByName(String name) {
        try {
            Pokemon pokemon = restTemplate.getForObject(url + "pokemon/" + name.toLowerCase().trim(), Pokemon.class);

            History history = new History();
            history.setPokemonName(pokemon.getName());
            history.setTimestamp(LocalDateTime.now());
            historyRepository.save(history);

            return pokemon;
        } catch (HttpClientErrorException.NotFound e) {
            throw new PokemonNotFoundException(name);
        }
    }

    public String addPokemonToPokedex(String name, String trainer) {
        Pokemon pokemon = getPokemonByName(name);

        Optional<Pokedex> pokedexOptional = pokedexRepository.findByTrainerName(trainer);
        Pokedex pokedex = pokedexOptional.orElseGet(Pokedex::new);

        if (pokedexOptional.isEmpty()) {
            pokedex.setTrainerName(trainer);            
        }

        pokedex.getPokemons().add(pokemon);
        pokedexRepository.save(pokedex);

        pokemon.setPokedex(pokedex);
        pokemonRepository.save(pokemon);

        return (pokemon.getName() + " adicionado à pokedex de " + trainer);
    }

    public String removePokemonFromPokedex(String name, String trainer) {
        Pokemon pokemon = getPokemonByName(name);
        Optional<Pokedex> pokedexOptional = pokedexRepository.findByTrainerName(trainer);
        Pokedex pokedex = pokedexOptional.orElseThrow(() -> new TrainerNotFoundException(trainer));

        List<Pokemon> pokemons = pokemonRepository.findByPokemon(pokemon.getId(), pokedex.getId());

        for(Pokemon p : pokemons){
            pokemonRepository.delete(p);
        }

        return (pokemon.getName() + " removido da pokedex de " + trainer);
    }

    public String getPokedexByTrainer(String trainer) {
        Optional<Pokedex> pokedexOptional = pokedexRepository.findByTrainerName(trainer);
        Pokedex pokedex = pokedexOptional.orElseThrow(() -> new TrainerNotFoundException(trainer));
        return pokedex.toString();
    }

    public String feedPokemon (PokemonActionRequestDto request) {
        Pokemon pokemon = getPokemonByName(request.getName());
        Optional<Pokedex> pokedexOptional = pokedexRepository.findByTrainerName(request.getTrainerName());
        Pokedex pokedex = pokedexOptional.orElseThrow(() -> new TrainerNotFoundException(request.getTrainerName()));

        List<Pokemon> pokemons = pokemonRepository.findByPokemon(pokemon.getId(), pokedex.getId());
        if(pokemons.isEmpty()){
            throw new PokemonNotFoundException(request.getName());
        }
        if ("feed".equalsIgnoreCase(request.getAction())) {
            for(Pokemon p : pokemons){
                p.setLastFed(LocalDateTime.now());
                pokemonRepository.save(p);
            }
            return (request.getName() + " alimentado");
        } else {
            throw new IllegalActionException(request.getAction());
        }
    }

    public Generation getGenerationById(Long id) {
        try {
            String response = restTemplate.getForObject(url + "generation/" + id, String.class);
            Generation generation = gson.fromJson(response, Generation.class);
            return generation;

        } catch (HttpClientErrorException.NotFound e) {
            throw new GenerationNotFoundException(id);
        }
    }

    public String saveGeneration (Long id) {
        Generation generation = getGenerationById(id);
        generationRepository.save(generation);
        return "Geração de id " + id + " salva com sucesso";
    }

    public String addGenerationToPokedex (Long id, String trainer) {
        Generation generation = getGenerationById(id);

        
        for (PokemonSpecies p: generation.getPokemonSpecies()) {
            if(!p.getName().equalsIgnoreCase("deoxys")){
                addPokemonToPokedex(p.getName(), trainer);
            }
        }
        return "Geração de id " + id + " adicionada à pokedex";
    }

}
