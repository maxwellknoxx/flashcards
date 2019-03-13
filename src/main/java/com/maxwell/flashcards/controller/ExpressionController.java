package com.maxwell.flashcards.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.maxwell.flashcards.entity.ExpressionEntity;
import com.maxwell.flashcards.response.Response;
import com.maxwell.flashcards.service.impl.ExpressionServiceImpl;

@RestController
public class ExpressionController {

	@Autowired
	private ExpressionServiceImpl service;
	
	@PostMapping(value = "/api/expression/findAll")
	public ResponseEntity<Response<ExpressionEntity>> findAll(@Valid @RequestBody ExpressionEntity entity, BindingResult result) {
		Response<ExpressionEntity> response = new Response<>();
		
		if(result.hasErrors()) {
			result.getAllErrors().forEach(errors -> response.getErrors().add(errors.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		List<ExpressionEntity> list = null;
		
		try {
		list = service.findAll();	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		response.setListData(list);
		
		return ResponseEntity.ok(response);
	}
	
	@PostMapping(value = "/api/expression/findByExpression")
	public ResponseEntity<Response<ExpressionEntity>> findByExpression(@Valid @RequestBody String expression, BindingResult result) {
		Response<ExpressionEntity> response = new Response<>();
		
		if(result.hasErrors()) {
			result.getAllErrors().forEach(errors -> response.getErrors().add(errors.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		ExpressionEntity  expressionEntity = null;
		try {
			expressionEntity = service.findByExpression(expression);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		response.setData(expressionEntity);
		
		return ResponseEntity.ok(response);
	}
	
	@PostMapping(value = "/api/expression/addExpression")
	public ResponseEntity<Response<ExpressionEntity>> addExpression(@Valid @RequestBody ExpressionEntity expressionEntity, BindingResult result) {
		Response<ExpressionEntity> response = new Response<>();
	
		if(result.hasErrors()) {
			result.getAllErrors().forEach(errors -> response.getErrors().add(errors.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		try {
			service.addexpression(expressionEntity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		response.setData(expressionEntity);
		response.setMessage("Sucess, " + expressionEntity.getExpression() + " has been added!");
		
		return ResponseEntity.ok(response); 
	}
	
	@PostMapping(value = "/api/expression/update")
	public ResponseEntity<Response<ExpressionEntity>> updateExpression(@Valid @RequestBody ExpressionEntity expressionEntity, BindingResult result) {
		Response<ExpressionEntity> response = new Response<>();
	
		if(result.hasErrors()) {
			result.getAllErrors().forEach(errors -> response.getErrors().add(errors.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		try {
			service.updateExpression(expressionEntity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		response.setData(expressionEntity);
		response.setMessage("Sucess, " + expressionEntity.getExpression() + " has been updated!");
		
		return ResponseEntity.ok(response); 
	}
	
	@PostMapping(value = "/api/expression/remove")
	public ResponseEntity<Response<ExpressionEntity>> removeExpression(@Valid @RequestBody ExpressionEntity expressionEntity, BindingResult result) {
		Response<ExpressionEntity> response = new Response<>();
	
		if(result.hasErrors()) {
			result.getAllErrors().forEach(errors -> response.getErrors().add(errors.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		try {
			service.removeExpressionById(expressionEntity.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		response.setData(expressionEntity);
		response.setMessage("Sucess, " + expressionEntity.getExpression() + " has been removed!");
		
		return ResponseEntity.ok(response); 
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
