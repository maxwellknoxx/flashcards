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
import com.maxwell.flashcards.exception.ResourceNotFoundException;
import com.maxwell.flashcards.model.Dictionary;
import com.maxwell.flashcards.service.impl.DictionaryServiceImpl;
import com.maxwell.flashcards.service.impl.MapValidationErrorService;
import com.maxwell.flashcards.util.Utils;

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
		Dictionary dictionary = Utils.convertDictionaryToModel(service.findDictionaryById(id));

		if (dictionary == null) {
			return new ResponseEntity<String>("dictionary not found", HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<Dictionary>(dictionary, HttpStatus.OK);
	}

	/**
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/api/v1/dictionary/dictionariesByUserId/{id}")
	public ResponseEntity<?> findDictionaryByUserId(@PathVariable(value = "id") Long id) {
		List<Dictionary> dictionaries = Utils.convertDictionaryToModelList(service.findDictionaryByUserId(id));
		if (dictionaries == null) {
			return new ResponseEntity<String>("Dictionaries not found for this user", HttpStatus.BAD_REQUEST);
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
	@PostMapping(value = "/api/v1/dictionary/findByDictionaryName")
	public ResponseEntity<?> findByDictionaryName(@Valid @RequestBody DictionaryEntity DictionaryToFind) {
		DictionaryEntity dictionary = service.findByDictionaryName(DictionaryToFind.getDictionaryName());

		if (dictionary == null) {
			return new ResponseEntity<String>("dictionary " + DictionaryToFind + " not found", HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<DictionaryEntity>(dictionary, HttpStatus.OK);
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

		Dictionary model;
		try {
			model = Utils.convertDictionaryToModel(service.addDictionary(request));
		} catch (Exception e) {
			return new ResponseEntity<String>("Something went wrong -> add dictionary " + e.getMessage(),
					HttpStatus.BAD_REQUEST);
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
	public ResponseEntity<?> updateDictionary(@Valid @RequestBody Dictionary model) {

		DictionaryEntity updatedDictionary = service.findDictionaryById(model.getId());

		updatedDictionary.setDictionaryName(model.getDictionaryName());

		try {
			service.addDictionary(updatedDictionary);
		} catch (Exception e) {
			return new ResponseEntity<String>("Something went wrong -> update dictionary " + e.getMessage(),
					HttpStatus.BAD_REQUEST);
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
			return new ResponseEntity<String>("Dictionary has been deleted", HttpStatus.OK);
		}

		return new ResponseEntity<String>("Something went wrong -> remove Dictionary", HttpStatus.BAD_REQUEST);
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

		DictionaryEntity dictionary;
		try {
			dictionary = service.findByDictionaryName(dictionaryName);
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<String>("Dictionary " + dictionaryName + " does not existe",
					HttpStatus.BAD_REQUEST);
		}
		totalHitWords = dictionary.getHitWords();
		totalFailWords = dictionary.getFailWords();

		totals.add(totalHitWords);
		totals.add(totalFailWords);

		return new ResponseEntity<List<Integer>>(totals, HttpStatus.OK);
	}

}
