package com.maxwell.flashcards.controller;

import java.util.Collections;
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

import com.maxwell.flashcards.entity.ExpressionEntity;
import com.maxwell.flashcards.model.Expression;
import com.maxwell.flashcards.service.impl.ExpressionServiceImpl;
import com.maxwell.flashcards.service.impl.MapValidationErrorService;

@CrossOrigin(origins = "*")
@RestController
public class ExpressionController {

	@Autowired
	private ExpressionServiceImpl service;

	@Autowired
	private MapValidationErrorService mapValidationErrorService;

	/**
	 * 
	 * Finds an expression
	 * 
	 * @param expressionName The expression name
	 * @param result
	 * @return
	 */
	@GetMapping(value = "/api/v1/expression/findByExpression")
	public ResponseEntity<?> findByExpression(@Valid @RequestBody Expression expressionToFind) {
		Expression expression = service.findByExpression(expressionToFind.getExpression());
		if (expression == null) {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}

		return new ResponseEntity<Expression>(expression, HttpStatus.OK);
	}

	/**
	 * 
	 * @param dictionary
	 * @param result
	 * @return
	 */
	@GetMapping(value = "/api/v1/expression/expressionsByDictionaryId/{id}")
	public ResponseEntity<?> findExpressionsByDictionaryId(@PathVariable(value = "id") long id) {
		List<Expression> list = service.findByDictionaryId(id);
		if (list == null) {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}
		Collections.shuffle(list);

		return new ResponseEntity<List<Expression>>(list, HttpStatus.OK);
	}

	/**
	 * 
	 * @param expression The current expression
	 * @param result
	 * @return
	 */
	@PostMapping(value = "/api/v1/expression/expressions")
	public ResponseEntity<?> addExpression(@Valid @RequestBody ExpressionEntity entity, BindingResult result) {
		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidation(result);
		if (errorMap != null) {
			return errorMap;
		}

		Expression expression = service.addExpression((entity));
		if (expression == null) {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}

		return new ResponseEntity<Expression>(expression, HttpStatus.CREATED);
	}

	/**
	 * Updates the current expression
	 * 
	 * @param expression The current expression
	 * @param result
	 * @return
	 */
	@PutMapping(value = "/api/v1/expression/expressions")
	public ResponseEntity<?> updateExpression(@Valid @RequestBody ExpressionEntity entity) {
		Expression expression = service.updateExpression(entity);
		if (expression == null) {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}

		return new ResponseEntity<Expression>(expression, HttpStatus.CREATED);
	}

	/**
	 * Deletes the current expression
	 * 
	 * @param expression The current expression
	 * @param result
	 * @return
	 */
	@DeleteMapping(value = "/api/v1/expression/expressions/{id}")
	public ResponseEntity<?> removeExpression(@Valid @PathVariable("id") Long id) {
		if (service.removeExpressionById(id)) {
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}

		return new ResponseEntity<Boolean>(false, HttpStatus.OK);
	}

}
