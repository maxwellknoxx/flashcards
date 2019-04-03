package com.maxwell.flashcards.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.maxwell.flashcards.entity.UserEntity;
import com.maxwell.flashcards.response.Response;
import com.maxwell.flashcards.service.impl.UserServiceImpl;

@CrossOrigin(origins = "*")
@RestController
public class UserController {

	@Autowired
	private UserServiceImpl service;

	@PostMapping(value = "/api/user/save")
	public ResponseEntity<Response<UserEntity>> addUser(@Valid @RequestBody UserEntity user, BindingResult result) {
		Response<UserEntity> response = new Response<UserEntity>();

		if (result.hasErrors()) {
			result.getAllErrors().forEach(errors -> response.getErrors().add(errors.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		try {
			service.save(user);
			response.setMessage("Success" + user.getUserName() + " Has been added!");
			response.setData(user);
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}

		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "/api/user/login")
	public ResponseEntity<Response<UserEntity>> login(@Valid @RequestBody UserEntity user, BindingResult result) {
		Response<UserEntity> response = new Response<UserEntity>();
		UserEntity userFromDB = new UserEntity();

		if (result.hasErrors()) {
			result.getAllErrors().forEach(errors -> response.getErrors().add(errors.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		try {
			userFromDB = service.findByUserName(user.getUserName());
			if (userFromDB.getPassword().equals(user.getPassword())) {
				response.setMessage("Logged in");
			}
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}

		return ResponseEntity.ok(response);
	}

}
