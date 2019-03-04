package com.miage.altea.tp.pokemon_type_api.repository;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miage.altea.tp.pokemon_type_api.bo.Translation;

@Repository
public class TranslationRepositoryImpl implements TranslationRepository {

	private Map<Locale, List<Translation>> translations;

	private List<Translation> defaultTranslations;

	@Autowired
	public TranslationRepositoryImpl() {
		try {
			var objectMapper = new ObjectMapper();

			var frenchTranslationStream = new ClassPathResource("translations-fr.json").getInputStream();
			var frenchTranslationsArray = objectMapper.readValue(frenchTranslationStream, Translation[].class);

			var englishTranslationStream = new ClassPathResource("translations-en.json").getInputStream();
			var englishTranslationsArray = objectMapper.readValue(englishTranslationStream, Translation[].class);

			this.translations = Map.of(
					Locale.FRENCH, List.of(frenchTranslationsArray),
					Locale.ENGLISH, List.of(englishTranslationsArray),
					Locale.US, List.of(englishTranslationsArray)
			);

			this.defaultTranslations = List.of(englishTranslationsArray);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getPokemonName(int id, Locale locale) {
		if(locale == null) {
			return defaultTranslations.stream().filter(t -> t.getId() == id).findFirst().get().getName();
		}
		else {
			return translations.get(locale).stream().filter(t -> t.getId() == id).findFirst().get().getName();
		}
	}

}
