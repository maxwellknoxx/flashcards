package com.maxwell.flashcards.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxwell.flashcards.entity.DictionaryEntity;
import com.maxwell.flashcards.model.Dictionary;
import com.maxwell.flashcards.repository.DictionaryRepository;
import com.maxwell.flashcards.service.DictionaryService;
import com.maxwell.flashcards.util.DictionaryMapper;

@Service
public class DictionaryServiceImpl implements DictionaryService {

	@Autowired
	private DictionaryRepository repository;

	public Dictionary addDictionary(DictionaryEntity dictionary) {
		DictionaryEntity entity = repository.save(dictionary);
		if (entity == null) {
			return null;
		}
		return DictionaryMapper.convertEntityToModel(entity);
	}

	public Boolean removeDictionaryById(Long id) {
		try {
			repository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Dictionary updateDictionary(DictionaryEntity dictionary) {
		DictionaryEntity entity = repository.save(dictionary);
		if (entity == null) {
			return null;
		}
		return DictionaryMapper.convertEntityToModel(entity);
	}
	
	public DictionaryEntity updateDictionaryEntity(DictionaryEntity dictionary) {
		DictionaryEntity entity = repository.save(dictionary);
		if (entity == null) {
			return null;
		}
		return entity;
	}

	@Override
	public List<Dictionary> findAll() {
		List<DictionaryEntity> list = repository.findAll();
		if(list.isEmpty()) {
			return null;
		}
		return  DictionaryMapper.convertEntityToModelList(list);
	}

	@Override
	public Dictionary findByDictionaryName(String dictionaryName) {
		DictionaryEntity dictionary = repository.findByDictionaryName(dictionaryName);
		if(dictionary == null) {
			return null;
		}
		return DictionaryMapper.convertEntityToModel(dictionary);
	}

	public DictionaryEntity getDictionaryName(String dictionaryName) {
		DictionaryEntity dictionary = repository.findByDictionaryName(dictionaryName);
		return dictionary;
	}

	@Override
	public Dictionary findDictionaryById(Long id) {
		DictionaryEntity dictionary = repository.findDictionaryById(id);
		if(dictionary == null) {
			return null;
		}
		return DictionaryMapper.convertEntityToModel(dictionary);
	}
	
	public DictionaryEntity getDictionaryById(Long id) {
		DictionaryEntity dictionary = repository.findDictionaryById(id);
		if(dictionary == null) {
			return null;
		}
		return dictionary;
	}

	@Override
	public List<Dictionary> findDictionaryByUserId(Long id) {
		List<DictionaryEntity> list = repository.findDictionaryByUserId(id);
		if(list.isEmpty()) {
			return null;
		}
		return  DictionaryMapper.convertEntityToModelList(list);
	}

}
