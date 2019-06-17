package com.maxwell.flashcards.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.maxwell.flashcards.entity.DictionaryEntity;
import com.maxwell.flashcards.entity.UserDictionaryEntity;
import com.maxwell.flashcards.response.Response;
import com.maxwell.flashcards.response.ResponseUtils;
import com.maxwell.flashcards.service.impl.DictionaryExpressionServiceImpl;
import com.maxwell.flashcards.service.impl.UserDictionaryServiceImpl;
import com.maxwell.flashcards.exception.ResourceNotFoundException;

@CrossOrigin("*")
@RestController
public class UserDictionaryController {

	@Autowired
	private UserDictionaryServiceImpl service;

	@Autowired
	private DictionaryExpressionServiceImpl dictionaryExpressionService;

	ResponseUtils responseUtils = new ResponseUtils();

	@GetMapping(value = "/api/userDictionary/findAllDictionaryByUserId/{id}")
	public ResponseEntity<Response<DictionaryEntity>> findAllDictionaryByUserId(@PathVariable(name = "id") Long id) {
		Response<DictionaryEntity> response = new Response<DictionaryEntity>();

		List<UserDictionaryEntity> list = null;
		List<DictionaryEntity> ListUserDictionary = null;
		try {
			list = service.findAllDictionaryByUserId(id);
			ListUserDictionary = findAll(list);
			response.setListData(ListUserDictionary);
			response = responseUtils.setMessages(response, "Resource found", "UserDictionaryController", true);
		} catch (Exception e) {
			throw new ResourceNotFoundException("Something went wrong! " + e.getMessage());
		}

		return ResponseEntity.ok(response);
	}

	public List<DictionaryEntity> findAll(List<UserDictionaryEntity> ListUserDictionary) {
		List<DictionaryEntity> list = new ArrayList<>();

		try {
			for (UserDictionaryEntity userDictionary : ListUserDictionary) {
				list.add(dictionaryExpressionService.findDictionaryById(userDictionary.getDictionaryId()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	@PostMapping(value = "/api/userDictionary/save")
	public ResponseEntity<Response<UserDictionaryEntity>> save(
			@Valid @RequestBody UserDictionaryEntity userDictionary) {
		Response<UserDictionaryEntity> response = new Response<UserDictionaryEntity>();

		try {
			userDictionary = service.save(userDictionary);
			response.setData(userDictionary);
			response = responseUtils.setMessages(response, "Saved", "UserDictionaryController",  true);
		} catch (Exception e) {
			throw new ResourceNotFoundException("Something went wrong! " + e.getMessage());
		}

		return ResponseEntity.ok(response);
	}

}
