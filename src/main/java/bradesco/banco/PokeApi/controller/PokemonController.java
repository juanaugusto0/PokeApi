package bradesco.banco.PokeApi.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bradesco.banco.PokeApi.dto.PokemonActionRequestDto;
import bradesco.banco.PokeApi.model.Pokemon;
import bradesco.banco.PokeApi.service.PokemonService;

@RestController
@RequestMapping("api/pokemon")
public class PokemonController {
    
    private final PokemonService pokemonService;

    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping("/{name}")
    public Pokemon getPokemonByName(@PathVariable String name) {
        return pokemonService.getPokemonByName(name);
    }

    @GetMapping("/pokedex/{trainer}")
    public String getPokedexByTrainer(@PathVariable String trainer) {
        return pokemonService.getPokedexByTrainer(trainer);
    }

    @PutMapping
    public String addPokemonToPokedex(@RequestParam String name, @RequestParam String trainer) {
        return pokemonService.addPokemonToPokedex(name, trainer);
    }

    @PostMapping("/feed")
    public String feedPokemon(@RequestBody PokemonActionRequestDto request) {
        return pokemonService.feedPokemon(request);
    }

    @DeleteMapping
    public String removePokemonFromPokedex(@RequestParam String name, @RequestParam String trainer) {
        return pokemonService.removePokemonFromPokedex(name, trainer);
    }
}
