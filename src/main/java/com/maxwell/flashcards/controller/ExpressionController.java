package com.maxwell.flashcards.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.maxwell.flashcards.model.Dictionary;
import com.maxwell.flashcards.model.Expression;
import com.maxwell.flashcards.response.Response;
import com.maxwell.flashcards.service.impl.DictionaryExpressionServiceImpl;
import com.maxwell.flashcards.service.impl.ExpressionServiceImpl;
import com.maxwell.flashcards.util.Utils;

@CrossOrigin(origins = "*")
@RestController
public class ExpressionController {

	@Autowired
	private ExpressionServiceImpl service;

	@Autowired
	private DictionaryExpressionServiceImpl dictionaryExpressionService;

	Utils util = new Utils();

	/**
	 * Finds and returns all expression in the dictionary
	 * 
	 * @param result
	 * @return
	 */
	@GetMapping(value = "/api/expression/findAll")
	public ResponseEntity<Response<Expression>> findAll() {
		Response<Expression> response = new Response<>();

		List<Expression> list = new ArrayList<>();

		try {
			service.findAll().forEach(expressionFromDB -> list.add(util.convertToModel(expressionFromDB)));
			response.setListData(list);
			response.setMessage("Resouce found");
		} catch (Exception e) {
			e.printStackTrace();
			response.getErrors().add(e.getCause().toString());
			return ResponseEntity.badRequest().body(response);
		}

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * Finds an expression
	 * 
	 * @param expressionName The expression name
	 * @param result
	 * @return
	 */
	@PostMapping(value = "/api/expression/findByExpression")
	public ResponseEntity<Response<Expression>> findByExpression(@Valid @RequestBody Expression expressionToFind,
			BindingResult result) {
		Response<Expression> response = new Response<>();

		if (result.hasErrors()) {
			result.getAllErrors().forEach(errors -> response.getErrors().add(errors.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		Expression expression = null;
		try {
			expression = util.convertToModel(service.findByExpression(expressionToFind.getExpression()));
			response.setData(expression);
			response.setMessage("Resouce found");
		} catch (Exception e) {
			e.printStackTrace();
			response.getErrors().add(e.getCause().toString());
			return ResponseEntity.badRequest().body(response);
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
	public ResponseEntity<Response<Expression>> addExpression(@Valid @RequestBody Expression expression,
			BindingResult result) {
		Response<Expression> response = new Response<>();

		if (result.hasErrors()) {
			result.getAllErrors().forEach(errors -> response.getErrors().add(errors.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}


		try {
			Dictionary dictionary = getDictionaryModel(Long.parseLong(expression.getDictionaryIdentityKey()));
			expression.setDictionary(dictionary);
			expression = util.convertToModel(service.addexpression(util.convertToEntity(expression)));
			response.setData(expression);
			response.setMessage("Sucess, " + expression.getExpression() + " has been added!");
		} catch (Exception e) {
			e.printStackTrace();
			response.getErrors().add(e.getCause().toString());
			return ResponseEntity.badRequest().body(response);
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
	public ResponseEntity<Response<Expression>> updateExpression(@Valid @RequestBody Expression expression,
			BindingResult result) {
		Response<Expression> response = new Response<>();

		if (result.hasErrors()) {
			result.getAllErrors().forEach(errors -> response.getErrors().add(errors.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		try {
			Dictionary dictionary = getDictionaryModel(Long.parseLong(expression.getDictionaryIdentityKey()));
			expression.setDictionary(dictionary);
			service.updateExpression(util.convertToEntity(expression));
			response.setData(expression);
			response.setMessage("Sucess, " + expression.getExpression() + " has been updated!");
		} catch (Exception e) {
			e.printStackTrace();
			response.getErrors().add(e.getCause().toString());
			return ResponseEntity.badRequest().body(response);
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
	public ResponseEntity<Response<Expression>> removeExpression(@Valid @RequestBody Expression expression,
			BindingResult result) {
		Response<Expression> response = new Response<>();

		if (result.hasErrors()) {
			result.getAllErrors().forEach(errors -> response.getErrors().add(errors.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		try {
			Dictionary dictionary = getDictionaryModel(Long.parseLong(expression.getDictionaryIdentityKey()));
			expression.setDictionary(dictionary);
			service.removeExpressionById(expression.getId());
			response.setData(expression);
			response.setMessage("Sucess, " + expression.getExpression() + " has been removed!");
		} catch (Exception e) {
			e.printStackTrace();
			response.getErrors().add(e.getCause().toString());
			return ResponseEntity.badRequest().body(response);
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
	public ResponseEntity<Response<Expression>> markAsHit(@Valid @RequestBody Expression expression,
			BindingResult result) {
		Response<Expression> response = new Response<>();

		if (result.hasErrors()) {
			result.getAllErrors().forEach(errors -> response.getErrors().add(errors.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		try {
			Dictionary dictionary = getDictionaryModel(Long.parseLong(expression.getDictionaryIdentityKey()));
			dictionary.addHitWords();
			expression.addHit();
			expression.setDictionary(dictionary);
			dictionaryExpressionService.updateHitsFails(util.convertToEntity(dictionary),
					util.convertToEntity(expression));
			response.setData(expression);
			response.setMessage("Sucess, " + expression.getExpression() + " has been hit!");
		} catch (Exception e) {
			e.printStackTrace();
			response.getErrors().add(e.getCause().toString());
			return ResponseEntity.badRequest().body(response);
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
	public ResponseEntity<Response<Expression>> markAsFail(@Valid @RequestBody Expression expression,
			BindingResult result) {
		Response<Expression> response = new Response<>();

		if (result.hasErrors()) {
			result.getAllErrors().forEach(errors -> response.getErrors().add(errors.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		try {
			Dictionary dictionary = getDictionaryModel(Long.parseLong(expression.getDictionaryIdentityKey()));
			dictionary.addFailWords();
			expression.addFail();
			expression.setDictionary(dictionary);
			dictionaryExpressionService.updateHitsFails(util.convertToEntity(dictionary),
					util.convertToEntity(expression));

			response.setData(expression);
			response.setMessage("Sucess, " + expression.getExpression() + " has been fail!");
		} catch (Exception e) {
			e.printStackTrace();
			response.getErrors().add(e.getCause().toString());
			return ResponseEntity.badRequest().body(response);
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
		dictionary = util.convertToModel(dictionaryExpressionService.findDictionaryById(dictionaryIdentityKey));
		return dictionary;
	}

}
