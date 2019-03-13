package com.maxwell.flashcards.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.maxwell.flashcards.entity.DictionaryEntity;
import com.maxwell.flashcards.response.Response;
import com.maxwell.flashcards.service.impl.DictionaryServiceImpl;
import com.maxwell.flashcards.util.Utils;

@RestController
public class DictionaryController {

	@Autowired
	private DictionaryServiceImpl service;
	
	Utils util = new Utils();
	
	@PostMapping(value = "/api/dictionary/findAll")
	public ResponseEntity<Response<DictionaryEntity>> findAll(@Valid @RequestBody DictionaryEntity dictionaryEntity, BindingResult result) {
		Response<DictionaryEntity> response = new Response<>();
		
		if(result.hasErrors()) {
			result.getAllErrors().forEach(errors -> response.getErrors().add(errors.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		List<DictionaryEntity> dictionaries = null;
		
		try {
			dictionaries = service.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		response.setListData(dictionaries);
		
		return ResponseEntity.ok(response);
	}
	
	@PostMapping(value = "/api/dictionary/findByDicionaryName")
	public ResponseEntity<Response<DictionaryEntity>> findByDicionaryName(@Valid @RequestParam String dictionaryName, BindingResult result) {
		Response<DictionaryEntity> response = new Response<>();
		
		if(result.hasErrors()) {
			result.getAllErrors().forEach(errors -> response.getErrors().add(errors.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		DictionaryEntity dictionary = new DictionaryEntity();
		
		try {
			dictionary = service.findByDictionaryName(dictionaryName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		response.setData(dictionary);
		
		return ResponseEntity.ok(response);
	}
	
	@PostMapping(value = "/api/dictionary/addDictionary")
	public ResponseEntity<Response<DictionaryEntity>> addDictionary(@Valid @RequestBody DictionaryEntity dictionaryEntity, BindingResult result) {
		Response<DictionaryEntity> response = new Response<>();
		
		if(result.hasErrors()) {
			result.getAllErrors().forEach(errors -> response.getErrors().add(errors.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		try {
			service.addDictionary(dictionaryEntity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		response.setData(dictionaryEntity);
		response.setMessage("Sucess, dictionary " +  dictionaryEntity.getDictionaryName() + " has been added!");
		
		return ResponseEntity.ok(response);
	}
	
	@PostMapping(value = "/api/dictionary/update")
	public ResponseEntity<Response<DictionaryEntity>> updateDictionary(@Valid @RequestBody DictionaryEntity dictionaryEntity, BindingResult result) {
		Response<DictionaryEntity> response = new Response<>();
		
		if(result.hasErrors()) {
			result.getAllErrors().forEach(errors -> response.getErrors().add(errors.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		try {
			service.updateDictionary(dictionaryEntity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		response.setData(dictionaryEntity);
		response.setMessage("Sucess, dictionary " +  dictionaryEntity.getDictionaryName() + " has been updated!");
		
		return ResponseEntity.ok(response);
	}
	
	@PostMapping(value = "/api/dictionary/remove")
	public ResponseEntity<Response<DictionaryEntity>> removeDictionary(@Valid @RequestBody DictionaryEntity dictionaryEntity, BindingResult result) {
		Response<DictionaryEntity> response = new Response<>();
		
		if(result.hasErrors()) {
			result.getAllErrors().forEach(errors -> response.getErrors().add(errors.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		try {
			service.removeDictionaryById(dictionaryEntity.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		response.setData(dictionaryEntity);
		response.setMessage("Sucess, dictionary " +  dictionaryEntity.getDictionaryName() + " has been removed!");
		
		return ResponseEntity.ok(response);
	}
	
	
	public List<Integer> totalHitWrong(String dictionaryName) {
		int totalHitWords = 0;
		int totalWrongWords = 0;
		List<Integer> totals = new ArrayList<>();
		
		DictionaryEntity dictionary = service.findByDictionaryName(dictionaryName);
		
		totalHitWords = dictionary.getHitWords();
		totalWrongWords = dictionary.getWrongWords();
		
		totals.add(totalHitWords);
		totals.add(totalWrongWords);
		
		return totals;
	}
}
