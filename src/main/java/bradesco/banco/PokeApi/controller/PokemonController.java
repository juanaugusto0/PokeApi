package bradesco.banco.PokeApi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bradesco.banco.PokeApi.model.Pokemon;
import bradesco.banco.PokeApi.service.PokemonService;

@RestController
@RequestMapping("api/pokemon")
public class PokemonController {
    
    private final PokemonService pokemonService;

    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping("/{id}")
    public Pokemon getPokemonById(@PathVariable Long id) {
        return pokemonService.getPokemonById(id);
    }
}
