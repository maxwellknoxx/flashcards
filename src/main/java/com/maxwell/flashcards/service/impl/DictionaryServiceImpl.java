package com.maxwell.flashcards.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxwell.flashcards.entity.DictionaryEntity;
import com.maxwell.flashcards.repository.DictionaryRepository;
import com.maxwell.flashcards.service.DictionaryService;

@Service
public class DictionaryServiceImpl implements DictionaryService {

	@Autowired
	private DictionaryRepository repository;
	
	public void addDictionary(DictionaryEntity dictionary) {
		repository.save(dictionary);
	}
	
	public void removeDictionaryById(Long id) {
		repository.deleteById(id);
	}
	
	public void updateDictionary(DictionaryEntity dictionary) {
		repository.save(dictionary);
	}
	
	public List<DictionaryEntity> findAll() {
		return repository.findAll();
	}
	
	@Override
	public int totalHitWords() {
		int totalHitWords = 0;
		
		List<DictionaryEntity> dictionary = repository.findAll();
		
		totalHitWords = dictionary.get(0).getHitWords();
		
		dictionary.get(0).getWrongWords();
		
		return totalHitWords;
	}

	@Override
	public int totalWrongWords() {
		int totalWrongWords = 0;
		
		return totalWrongWords;
	}
	
	
}
