package com.maxwell.flashcards.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.maxwell.flashcards.entity.DictionaryEntity;
import com.maxwell.flashcards.entity.UserDictionaryEntity;
import com.maxwell.flashcards.model.Dictionary;
import com.maxwell.flashcards.response.Response;
import com.maxwell.flashcards.service.impl.DictionaryServiceImpl;
import com.maxwell.flashcards.service.impl.UserDictionaryServiceImpl;
import com.maxwell.flashcards.util.Utils;

@CrossOrigin(origins = "*")
@RestController
public class DictionaryController {

	@Autowired
	private DictionaryServiceImpl service;

	@Autowired
	private UserDictionaryServiceImpl userDictionaryService;

	Utils util = new Utils();

	/**
	 * Finds and returns all dictionaries
	 * 
	 * @param result
	 * @return
	 */
	@GetMapping(value = "/api/dictionary/findAll")
	public ResponseEntity<Response<Dictionary>> findAll() {
		Response<Dictionary> response = new Response<>();

		List<Dictionary> dictionaries = new ArrayList<>();

		try {
			service.findAll().forEach(dictionariesFromDB -> dictionaries.add(util.convertToModel(dictionariesFromDB)));
			response.setListData(dictionaries);
			response.setMessage("Resource found");
		} catch (Exception e) {
			e.printStackTrace();
			response.getErrors().add(e.getCause().toString());
			return ResponseEntity.badRequest().body(response);
		}

		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "/api/dictionary/findDictionaryById/{id}")
	public ResponseEntity<Response<Dictionary>> findDictionaryById(@PathVariable(value = "id") Long id) {
		Response<Dictionary> response = new Response<>();

		Dictionary dictionary = new Dictionary();

		try {
			dictionary = util.convertToModel(service.findDictionaryById(id));
			response.setData(dictionary);
			response.setMessage("Resource found");
		} catch (Exception e) {
			e.printStackTrace();
			response.getErrors().add(e.getCause().toString());
			return ResponseEntity.badRequest().body(response);
		}

		return ResponseEntity.ok(response);
	}

	/**
	 * Returns the dictionary with the typed name
	 * 
	 * @param dictionaryName
	 * @param result
	 * @return
	 */
	@GetMapping(value = "/api/dictionary/findByDicionaryName")
	public ResponseEntity<Response<Dictionary>> findByDictionaryName(@Valid @RequestParam Dictionary DictionaryToFind,
			BindingResult result) {
		Response<Dictionary> response = new Response<>();

		if (result.hasErrors()) {
			result.getAllErrors().forEach(errors -> response.getErrors().add(errors.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		Dictionary dictionary = new Dictionary();

		try {
			dictionary = util.convertToModel(service.findByDictionaryName(DictionaryToFind.getDictionaryName()));
			response.setData(dictionary);
			response.setMessage("Resource found");
		} catch (Exception e) {
			e.printStackTrace();
			response.getErrors().add(e.getCause().toString());
			return ResponseEntity.badRequest().body(response);
		}

		return ResponseEntity.ok(response);
	}

	/**
	 * Adds a new dictionary to the database
	 * 
	 * @param dictionary
	 * @param result
	 * @return
	 */
	@PostMapping(value = "/api/dictionary/addDictionary")
	public ResponseEntity<Response<Dictionary>> addDictionary(@Valid @RequestBody Dictionary dictionary,
			BindingResult result) {
		Response<Dictionary> response = new Response<>();
		DictionaryEntity entity =  new DictionaryEntity();
		UserDictionaryEntity userDictionary = new UserDictionaryEntity();
		Long userId = dictionary.getIdUser();

		if (result.hasErrors()) {
			result.getAllErrors().forEach(errors -> response.getErrors().add(errors.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		entity = util.convertToEntity(dictionary);


		try {
			dictionary = util.convertToModel(service.addDictionary(entity));
			if (dictionary.getId() != null) {
				userDictionary.setDictionaryId(dictionary.getId());
				userDictionary.setUserId(userId);
				userDictionaryService.save(userDictionary);
			}
			response.setData(dictionary);
			response.setMessage("Sucess, dictionary " + dictionary.getDictionaryName() + " has been added!");
		} catch (Exception e) {
			e.printStackTrace();
			response.getErrors().add(e.getCause().toString());
			return ResponseEntity.badRequest().body(response);
		}

		return ResponseEntity.ok(response);
	}

	/**
	 * Updates the dictionary
	 * 
	 * @param dictionary
	 * @param result
	 * @return
	 */
	@PutMapping(value = "/api/dictionary/update")
	public ResponseEntity<Response<Dictionary>> updateDictionary(@Valid @RequestBody Dictionary dictionary,
			BindingResult result) {
		Response<Dictionary> response = new Response<>();

		if (result.hasErrors()) {
			result.getAllErrors().forEach(errors -> response.getErrors().add(errors.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		try {
			service.updateDictionary(util.convertToEntity(dictionary));
			response.setData(dictionary);
			response.setMessage("Sucess, dictionary " + dictionary.getDictionaryName() + " has been updated!");
		} catch (Exception e) {
			e.printStackTrace();
			response.getErrors().add(e.getCause().toString());
			return ResponseEntity.badRequest().body(response);
		}

		return ResponseEntity.ok(response);
	}

	/**
	 * Removes the dictionary
	 * 
	 * @param dictionary
	 * @param result
	 * @return
	 */
	@PostMapping(value = "/api/dictionary/remove")
	public ResponseEntity<Response<Dictionary>> removeDictionary(@Valid @RequestBody Dictionary dictionary,
			BindingResult result) {
		Response<Dictionary> response = new Response<>();

		if (result.hasErrors()) {
			result.getAllErrors().forEach(errors -> response.getErrors().add(errors.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		try {
			service.removeDictionaryById(dictionary.getId());
			response.setData(dictionary);
			response.setMessage("Sucess, dictionary " + dictionary.getDictionaryName() + " has been removed!");
		} catch (Exception e) {
			e.printStackTrace();
			response.getErrors().add(e.getCause().toString());
			return ResponseEntity.badRequest().body(response);
		}

		return ResponseEntity.ok(response);
	}

	/**
	 * Checks the total of hit and fail words in specific dictionary
	 * 
	 * @param dictionaryName
	 * @return
	 */
	@GetMapping(value = "/api/dictionary/totalHitFail")
	public ResponseEntity<Response<List<Integer>>> totalHitFail(String dictionaryName) {
		Response<List<Integer>> response = new Response<>();
		int totalHitWords = 0;
		int totalFailWords = 0;
		List<Integer> totals = new ArrayList<>();

		Dictionary dictionary = util.convertToModel(service.findByDictionaryName(dictionaryName));

		totalHitWords = dictionary.getHitWords();
		totalFailWords = dictionary.getFailWords();

		totals.add(totalHitWords);
		totals.add(totalFailWords);

		response.setData(totals);

		return ResponseEntity.ok(response);
	}
}
