package com.miage.altea.tp.pokemon_type_api.service;

import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.miage.altea.tp.pokemon_type_api.bo.PokemonType;
import com.miage.altea.tp.pokemon_type_api.repository.PokemonTypeRepository;
import com.miage.altea.tp.pokemon_type_api.repository.TranslationRepository;

@Service
public class PokemonTypeServiceImpl implements PokemonTypeService {

	public PokemonTypeRepository pokemonTypeRepository;
	public TranslationRepository translationRepository;

	@Autowired
	public PokemonTypeServiceImpl(PokemonTypeRepository repository, TranslationRepository tr){
		this.pokemonTypeRepository = repository;
		this.translationRepository = tr;
	}

	public PokemonTypeServiceImpl() {
	}

	public PokemonTypeServiceImpl(PokemonTypeRepository pokemonTypeRepository) {
		this.pokemonTypeRepository = pokemonTypeRepository;
	}

	@Override
	public PokemonType getPokemonType(int id) {
		if (translationRepository == null ) {
			PokemonType pt = new PokemonType();
			pt.setId(id);
			return pt;
		} else {
			Locale locale = LocaleContextHolder.getLocale();
			PokemonType pt = pokemonTypeRepository.findPokemonTypeById(id);
			pt.setName(translationRepository.getPokemonName(id, locale));
			return pt;
		}
	}

	@Override
	public PokemonType getPokemonTypeByName(String name) {
		return pokemonTypeRepository.findPokemonTypeByName(name);
	}

	@Override
	public List<PokemonType> getAllPokemonTypes(){
		Locale locale = LocaleContextHolder.getLocale();
		return pokemonTypeRepository.findAllPokemonType().stream().map(t -> {t.setName(translationRepository.getPokemonName(t.getId(), locale)); return t;})
				.sorted(Comparator.comparing(PokemonType::getId))
				.collect(Collectors.toList());
	}

	public PokemonTypeRepository getPokemonTypeRepository() {
		return pokemonTypeRepository;
	}

	public void setPokemonTypeRepository(PokemonTypeRepository pokemonTypeRepository) {
		this.pokemonTypeRepository = pokemonTypeRepository;
	}

	public TranslationRepository getTranslationRepository() {
		return translationRepository;
	}

	public void setTranslationRepository(TranslationRepository translationRepository) {
		this.translationRepository = translationRepository;
	}
}
