package com.maxwell.flashcards.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.maxwell.flashcards.model.Dictionary;
import com.maxwell.flashcards.model.Expression;
import com.maxwell.flashcards.response.Response;
import com.maxwell.flashcards.response.ResponseUtils;
import com.maxwell.flashcards.service.impl.DictionaryExpressionServiceImpl;
import com.maxwell.flashcards.service.impl.ExpressionServiceImpl;
import com.maxwell.flashcards.util.Utils;
import com.maxwell.flashcards.exception.ResourceNotFoundException;

@CrossOrigin(origins = "*")
@RestController
public class ExpressionController {

	@Autowired
	private ExpressionServiceImpl service;

	@Autowired
	private DictionaryExpressionServiceImpl dictionaryExpressionService;

	ResponseUtils responseUtils = new ResponseUtils();

	Utils util = new Utils();

	/**
	 * 
	 * Finds an expression
	 * 
	 * @param expressionName The expression name
	 * @param result
	 * @return
	 */
	@GetMapping(value = "/api/expression/findByExpression")
	public ResponseEntity<Response<Expression>> findByExpression(@Valid @RequestBody Expression expressionToFind) {
		Response<Expression> response = new Response<>();

		Expression expression = null;
		try {
			expression = util.convertToModel(service.findByExpression(expressionToFind.getExpression()));
			response.setData(expression);
			response = responseUtils.setMessages(response, "Resource found", "ExpressionController", true);
		} catch (Exception e) {
			throw new ResourceNotFoundException("Something went wrong! " + e.getMessage());
		}

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * @param dictionary
	 * @param result
	 * @return
	 */
	@GetMapping(value = "/api/expression/findExpressionsByDictionaryId/{id}")
	public ResponseEntity<Response<Expression>> findExpressionsByDictionaryId(@PathVariable(value = "id") long id) {
		Response<Expression> response = new Response<>();

		List<Expression> list = new ArrayList<>();
		try {
			dictionaryExpressionService.findByDictionaryId(id).forEach(expressionFromDB -> {
				list.add(util.convertToModel(expressionFromDB));
			});
			Collections.shuffle(list);
			response.setListData(list);
			response = responseUtils.setMessages(response, "Resource found", "ExpressionController", true);
		} catch (Exception e) {
			throw new ResourceNotFoundException("Something went wrong! " + e.getMessage());
		}

		return ResponseEntity.ok(response);
	}

	/**
	 * Adds the current expression to the dictionary
	 * 
	 * @param expression The current expression
	 * @param result
	 * @return
	 */
	@PostMapping(value = "/api/expression/addExpression")
	public ResponseEntity<Response<Expression>> addExpression(@Valid @RequestBody Expression expression) {
		Response<Expression> response = new Response<>();

		try {
			Dictionary dictionary = getDictionaryModel(Long.parseLong(expression.getDictionaryIdentityKey()));
			expression.setDictionary(dictionary);
			expression = util.convertToModel(service.addexpression(util.convertToEntity(expression)));
			response.setData(expression);
			response = responseUtils.setMessages(response, "Sucess, " + expression.getExpression() + " has been added!", "ExpressionController",
					true);
		} catch (Exception e) {
			throw new ResourceNotFoundException("Something went wrong! " + e.getMessage());
		}

		return ResponseEntity.ok(response);
	}

	/**
	 * Updates the current expression
	 * 
	 * @param expression The current expression
	 * @param result
	 * @return
	 */
	@PutMapping(value = "/api/expression/update")
	public ResponseEntity<Response<Expression>> updateExpression(@Valid @RequestBody Expression expression) {
		Response<Expression> response = new Response<>();

		try {
			Dictionary dictionary = getDictionaryModel(Long.parseLong(expression.getDictionaryIdentityKey()));
			expression.setDictionary(dictionary);
			service.updateExpression(util.convertToEntity(expression));
			response.setData(expression);
			response = responseUtils.setMessages(response,
					"Sucess, " + expression.getExpression() + " has been updated!", "ExpressionController", true);
		} catch (Exception e) {
			throw new ResourceNotFoundException("Something went wrong! " + e.getMessage());
		}

		return ResponseEntity.ok(response);
	}

	/**
	 * Deletes the current expression
	 * 
	 * @param expression The current expression
	 * @param result
	 * @return
	 */
	@PostMapping(value = "/api/expression/remove")
	public ResponseEntity<Response<Expression>> removeExpression(@Valid @RequestBody Expression expression) {
		Response<Expression> response = new Response<>();

		try {
			Dictionary dictionary = getDictionaryModel(Long.parseLong(expression.getDictionaryIdentityKey()));
			expression.setDictionary(dictionary);
			service.removeExpressionById(expression.getId());
			response.setData(expression);
			response = responseUtils.setMessages(response,
					"Sucess, " + expression.getExpression() + " has been removed!", "ExpressionController", true);
		} catch (Exception e) {
			throw new ResourceNotFoundException("Something went wrong! " + e.getMessage());
		}

		return ResponseEntity.ok(response);
	}

	/**
	 * Adds a hit mark to the expression
	 * 
	 * @param expression The current expression
	 * @param result
	 * @return
	 */
	@PostMapping(value = "/api/expression/hit")
	public ResponseEntity<Response<Expression>> markAsHit(@Valid @RequestBody Expression expression) {
		Response<Expression> response = new Response<>();

		try {
			Dictionary dictionary = getDictionaryModel(Long.parseLong(expression.getDictionaryIdentityKey()));
			dictionary.addHitWords();
			expression.addHit();
			expression.setDictionary(dictionary);
			dictionaryExpressionService.updateHitsFails(util.convertToEntity(dictionary),
					util.convertToEntity(expression));
			response.setData(expression);
			response = responseUtils.setMessages(response, "Sucess, " + expression.getExpression() + " has been hit!", "ExpressionController",
					true);
		} catch (Exception e) {
			throw new ResourceNotFoundException("Something went wrong! " + e.getMessage());
		}

		return ResponseEntity.ok(response);
	}

	/**
	 * Adds a fail mark to the expression
	 * 
	 * @param expression the Current expression
	 * @param result
	 * @return
	 */
	@PostMapping(value = "/api/expression/fail")
	public ResponseEntity<Response<Expression>> markAsFail(@Valid @RequestBody Expression expression) {
		Response<Expression> response = new Response<>();

		try {
			Dictionary dictionary = getDictionaryModel(Long.parseLong(expression.getDictionaryIdentityKey()));
			dictionary.addFailWords();
			expression.addFail();
			expression.setDictionary(dictionary);
			dictionaryExpressionService.updateHitsFails(util.convertToEntity(dictionary),
					util.convertToEntity(expression));

			response.setData(expression);
			response = responseUtils.setMessages(response, "Sucess, " + expression.getExpression() + " has been fail!", "ExpressionController",
					true);
		} catch (Exception e) {
			throw new ResourceNotFoundException("Something went wrong! " + e.getMessage());
		}

		return ResponseEntity.ok(response);
	}

	/**
	 * Gets the dictionary converted to Model
	 * 
	 * @param dictionaryIdentityKey
	 * @return
	 */
	public Dictionary getDictionaryModel(Long dictionaryIdentityKey) {
		Dictionary dictionary = new Dictionary();
		try {
			dictionary = util.convertToModel(dictionaryExpressionService.findDictionaryById(dictionaryIdentityKey));
		} catch (Exception e) {
			throw new ResourceNotFoundException("Something went wrong! " + e.getMessage());
		}
		return dictionary;
	}

}
