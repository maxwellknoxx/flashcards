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

	public Boolean removeDictionaryById(Long id) throws ResourceNotFoundException {
		DictionaryEntity dictionary = repository.findDictionaryById(id);
		if (Objects.isNull(dictionary)) {
			return false;
		}
		repository.deleteById(id);
		return true;
	}

	public DictionaryEntity updateDictionary(DictionaryEntity dictionary) {
		try {
			return repository.save(dictionary);
		} catch (Exception e) {
			throw new ResourceNotFoundException("Something went wrong -> update dictionary -> " + e.getMessage());
		}
	}

	@Override
	public List<DictionaryEntity> findAll() throws ResourceNotFoundException{
		List<DictionaryEntity> list = repository.findAll();
		if (list == null) {
			return null;
		}
		return list;
	}

	@Override
	public DictionaryEntity findByDictionaryName(String dictionaryName) throws ResourceNotFoundException {
		DictionaryEntity dictionary = repository.findByDictionaryName(dictionaryName);
		if (Objects.isNull(dictionary)) {
			return null;
		}
		return dictionary;
	}

	@Override
	public DictionaryEntity findDictionaryById(Long id) throws ResourceNotFoundException {
		DictionaryEntity dictionary = repository.findDictionaryById(id);
		if (Objects.isNull(dictionary)) {
			return null;
		}
		return dictionary;
	}

	@Override
	public List<DictionaryEntity> findDictionaryByUserId(Long id) throws ResourceNotFoundException {
		List<DictionaryEntity> dictionary = repository.findDictionaryByUserId(id);
		if (Objects.isNull(dictionary)) {
			return null;
		}
		return dictionary;
	}

}
