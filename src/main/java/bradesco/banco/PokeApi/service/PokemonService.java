package bradesco.banco.PokeApi.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import bradesco.banco.PokeApi.model.Pokemon;


@Service
public class PokemonService {
    private final RestTemplate restTemplate;
    private final String url = "https://pokeapi.co/api/v2/";

    public PokemonService() {
        this.restTemplate = new RestTemplate();
    }

    public Pokemon getPokemonById(Long id) {
        return restTemplate.getForObject(url + "pokemon/" + id, Pokemon.class);
    }
    public Pokemon getPokemonByName(String name) {
        return restTemplate.getForObject(url + "pokemon/" + name, Pokemon.class);
    }
}
