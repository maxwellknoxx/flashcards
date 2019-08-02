package com.maxwell.flashcards.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.maxwell.flashcards.entity.DictionaryEntity;
import com.maxwell.flashcards.entity.ExpressionEntity;
import com.maxwell.flashcards.model.Expression;
import com.maxwell.flashcards.service.impl.DictionaryServiceImpl;
import com.maxwell.flashcards.service.impl.ExpressionServiceImpl;
import com.maxwell.flashcards.util.Utils;

@RestController
@CrossOrigin
public class DictionaryExpressionController {

	@Autowired
	private DictionaryServiceImpl dictionaryService;

	@Autowired
	private ExpressionServiceImpl expressionService;

	/**
	 * 
	 * @param entity
	 * @return
	 */
	@PostMapping(path = "/api/v1/dictionaryExpression/hit")
	public ResponseEntity<?> hitWord(@Valid @RequestBody ExpressionEntity entity) {
		entity.addHit();
		entity.setDictionary(hitDictionary(entity.getDictionary().getId()));
		Expression expression = Utils.convertExpressionToModel(expressionService.updateExpression(entity));

		return new ResponseEntity<Expression>(expression, HttpStatus.OK);
	}

	/**
	 * 
	 * @param entity
	 * @return
	 */
	@PostMapping(path = "/api/v1/dictionaryExpression/fail")
	public ResponseEntity<?> failWord(@Valid @RequestBody ExpressionEntity entity) {
		entity.addFail();
		entity.setDictionary(failDictionary(entity.getDictionary().getId()));
		Expression expression = Utils.convertExpressionToModel(expressionService.updateExpression(entity));

		return new ResponseEntity<Expression>(expression, HttpStatus.OK);
	}

	public DictionaryEntity hitDictionary(Long id) {
		DictionaryEntity entity = getDictionary(id);
		entity.addHit();
		return dictionaryService.updateDictionary(entity);
	}

	public DictionaryEntity failDictionary(Long id) {
		DictionaryEntity entity = getDictionary(id);
		entity.addFail();
		return dictionaryService.updateDictionary(entity);
	}

	public DictionaryEntity getDictionary(Long id) {
		return dictionaryService.findDictionaryById(id);
	}

}
