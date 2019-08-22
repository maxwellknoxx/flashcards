package com.maxwell.flashcards.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.maxwell.flashcards.entity.DictionaryEntity;
import com.maxwell.flashcards.model.Dictionary;
import com.maxwell.flashcards.service.impl.DictionaryServiceImpl;
import com.maxwell.flashcards.service.impl.MapValidationErrorService;

@CrossOrigin(origins = "*")
@RestController
public class DictionaryController {

	@Autowired
	private DictionaryServiceImpl service;

	@Autowired
	private MapValidationErrorService mapValidationErrorService;

	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/api/v1/dictionary/dictionaries/{id}")
	public ResponseEntity<?> findDictionaryById(@PathVariable(value = "id") Long id) {
		Dictionary dictionary = service.findDictionaryById(id);

		if (dictionary == null) {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}

		return new ResponseEntity<Dictionary>(dictionary, HttpStatus.OK);
	}

	/**
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/api/v1/dictionary/dictionariesByUserId/{id}")
	public ResponseEntity<?> findDictionaryByUserId(@PathVariable(value = "id") Long id) {
		List<Dictionary> dictionaries = service.findDictionaryByUserId(id);
		if (dictionaries == null) {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}

		return new ResponseEntity<List<Dictionary>>(dictionaries, HttpStatus.OK);
	}

	/**
	 * Returns the dictionary with the typed name
	 * 
	 * @param dictionaryName
	 * @param result
	 * @return
	 */
	@PostMapping(value = "/api/v1/dictionary/dictionariesByName")
	public ResponseEntity<?> findByDictionaryName(@Valid @RequestBody DictionaryEntity DictionaryToFind) {
		Dictionary dictionary = service.findByDictionaryName(DictionaryToFind.getDictionaryName());

		if (dictionary == null) {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}

		return new ResponseEntity<Dictionary>(dictionary, HttpStatus.OK);
	}

	/**
	 * Adds a new dictionary to the database
	 * 
	 * @param dictionary
	 * @param result
	 * @return
	 */
	@PostMapping(value = "/api/v1/dictionary/dictionaries")
	public ResponseEntity<?> addDictionary(@Valid @RequestBody DictionaryEntity request, BindingResult result) {

		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidation(result);
		if (errorMap != null) {
			return errorMap;
		}

		Dictionary model = service.addDictionary(request);
		if (model == null) {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}

		return new ResponseEntity<Dictionary>(model, HttpStatus.CREATED);
	}

	/**
	 * Updates the dictionary
	 * 
	 * @param dictionary
	 * @param result
	 * @return
	 */
	@PutMapping(value = "/api/v1/dictionary/dictionaries")
	public ResponseEntity<?> updateDictionary(@Valid @RequestBody DictionaryEntity entity) {

		Dictionary model = service.addDictionary(entity);
		if (model == null) {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}

		return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
	}

	/**
	 * Removes the dictionary
	 * 
	 * @param dictionary
	 * @param result
	 * @return
	 */
	@DeleteMapping(value = "/api/v1/dictionary/dictionaries/{id}")
	public ResponseEntity<?> removeDictionary(@Valid @PathVariable("id") Long id) {
		if (service.removeDictionaryById(id)) {
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}

		return new ResponseEntity<Boolean>(false, HttpStatus.OK);
	}

	/**
	 * Checks the total of hit and fail words in specific dictionary
	 * 
	 * @param dictionaryName
	 * @return
	 */
	@GetMapping(value = "/api/v1/dictionary/totals")
	public ResponseEntity<?> totalHitFail(String dictionaryName) {
		int totalHitWords = 0;
		int totalFailWords = 0;
		List<Integer> totals = new ArrayList<>();

		DictionaryEntity dictionary = service.getDictionaryName(dictionaryName);
		
		totalHitWords = dictionary.getHitWords();
		totalFailWords = dictionary.getFailWords();

		totals.add(totalHitWords);
		totals.add(totalFailWords);

		return new ResponseEntity<List<Integer>>(totals, HttpStatus.OK);
	}

}
