package com.maxwell.flashcards.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxwell.flashcards.entity.DictionaryEntity;
import com.maxwell.flashcards.exception.ResourceNotFoundException;
import com.maxwell.flashcards.repository.DictionaryRepository;
import com.maxwell.flashcards.service.DictionaryService;

@Service
public class DictionaryServiceImpl implements DictionaryService {

	@Autowired
	private DictionaryRepository repository;

	public DictionaryEntity addDictionary(DictionaryEntity dictionary) {
		try {
			return repository.save(dictionary);
		} catch (Exception e) {
			throw new ResourceNotFoundException("Something went wrong -> save dictionary -> " + e.getMessage());
		}
	}

	public void removeDictionaryById(Long id) {
		DictionaryEntity dictionary = repository.findDictionaryById(id);
		if (Objects.isNull(dictionary)) {
			throw new ResourceNotFoundException("Dictionary " + id + " does not exist");
		}
		repository.deleteById(id);
	}

	public DictionaryEntity updateDictionary(DictionaryEntity dictionary) {
		try {
			return repository.save(dictionary);
		} catch (Exception e) {
			throw new ResourceNotFoundException("Something went wrong -> update dictionary -> " + e.getMessage());
		}
	}

	@Override
	public List<DictionaryEntity> findAll() {
		List<DictionaryEntity> list = repository.findAll();
		if (list == null) {
			throw new ResourceNotFoundException("Something went wrong -> findAll");
		}
		return list;
	}

	@Override
	public DictionaryEntity findByDictionaryName(String dictionaryName) {
		DictionaryEntity dictionary = repository.findByDictionaryName(dictionaryName);
		if (Objects.isNull(dictionary)) {
			throw new ResourceNotFoundException("Dictionary " + dictionaryName + " does not exist");
		}
		return dictionary;
	}

	@Override
	public DictionaryEntity findDictionaryById(Long id) {
		DictionaryEntity dictionary = repository.findDictionaryById(id);
		if (Objects.isNull(dictionary)) {
			throw new ResourceNotFoundException("Dictionary " + id + " does not exist");
		}
		return dictionary;
	}

}
