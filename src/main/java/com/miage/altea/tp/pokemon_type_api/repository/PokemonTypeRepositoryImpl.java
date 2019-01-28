package com.miage.altea.tp.pokemon_type_api.repository;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miage.altea.tp.pokemon_type_api.bo.PokemonType;

@Repository
public class PokemonTypeRepositoryImpl
		implements PokemonTypeRepository {

	private List<PokemonType> pokemons;

	public PokemonTypeRepositoryImpl() {
		try {
			var pokemonsStream = this.getClass().getResourceAsStream("/pokemons.json");

			var objectMapper = new ObjectMapper();
			var pokemonsArray = objectMapper.readValue(pokemonsStream, PokemonType[].class);
			this.pokemons = Arrays.asList(pokemonsArray);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public PokemonType findPokemonTypeById(int id) {
		System.out.println("Loading Pokemon information for Pokemon id " + id);
		return this.pokemons.stream().filter(x -> x.getId() == id).findFirst().get();
	}

	@Override
	public PokemonType findPokemonTypeByName(String name) {
		System.out.println("Loading Pokemon information for Pokemon name " + name);
		return this.pokemons.stream().filter(x -> name.equals(x.getName())).findFirst().get();
	}

	@Override
	public List<PokemonType> findAllPokemonType() {
		return this.pokemons;
	}

}
