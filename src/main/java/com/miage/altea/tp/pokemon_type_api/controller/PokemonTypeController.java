package com.miage.altea.tp.pokemon_type_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.miage.altea.tp.pokemon_type_api.bo.PokemonType;
import com.miage.altea.tp.pokemon_type_api.service.PokemonTypeService;

@RestController
@RequestMapping("/pokemon-types")
public class PokemonTypeController {

	private PokemonTypeService pokemonTypeService;

	@Autowired
	public PokemonTypeController(PokemonTypeService service) {
		this.pokemonTypeService = service;
	}

	@GetMapping("/{id}")
	PokemonType getPokemonTypeFromId(@PathVariable("id") int id){
		return pokemonTypeService.getPokemonType(id);
	}

	@GetMapping("/")
	@RequestMapping(value = "/", params = "name")
	PokemonType getPokemonTypeFromName(@RequestParam(value="name") String name){
		return pokemonTypeService.getPokemonTypeByName(name);
	}

	@GetMapping("/")
	public List<PokemonType> getAllPokemonTypes() {
		return pokemonTypeService.getAllPokemonTypes();
	}

}
