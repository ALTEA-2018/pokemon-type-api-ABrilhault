package com.miage.altea.tp.pokemon_type_api.repository;

import java.util.List;

import com.miage.altea.tp.pokemon_type_api.bo.PokemonType;

public interface PokemonTypeRepository {

	PokemonType findPokemonTypeById(int id);
	PokemonType findPokemonTypeByName(String name);
	List<PokemonType> findAllPokemonType();

}
