package com.miage.altea.tp.pokemon_type_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miage.altea.tp.pokemon_type_api.bo.PokemonType;
import com.miage.altea.tp.pokemon_type_api.repository.PokemonTypeRepository;

@Service
public class PokemonTypeServiceImpl implements PokemonTypeService {

	public PokemonTypeRepository pokemonTypeRepository;

	@Autowired
	public PokemonTypeServiceImpl(PokemonTypeRepository repository){
		this.pokemonTypeRepository = repository;
	}


	@Override
	public PokemonType getPokemonType(int id) {
		return pokemonTypeRepository.findPokemonTypeById(id);
	}

	@Override
	public List<PokemonType> getAllPokemonTypes(){
		return pokemonTypeRepository.findAllPokemonType();
	}

}
