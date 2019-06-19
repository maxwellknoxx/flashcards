package com.maxwell.flashcards.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.maxwell.flashcards.entity.UserEntity;
import com.maxwell.flashcards.model.Data;
import com.maxwell.flashcards.response.Response;
import com.maxwell.flashcards.response.ResponseUtils;
import com.maxwell.flashcards.service.impl.UserServiceImpl;
import com.maxwell.flashcards.exception.ResourceNotFoundException;

@CrossOrigin(origins = "*")
@RestController
public class UserController {

	@Autowired
	private UserServiceImpl service;

	ResponseUtils responseUtils = new ResponseUtils();

	@PostMapping(value = "/api/user/save")
	public ResponseEntity<Response<Data>> addUser(@Valid @RequestBody UserEntity user) {
		Response<Data> response = new Response<Data>();
		Data data = new Data();
		user.setIsLogged(true);

		try {
			service.save(user);
			data.setId(user.getId());
			data.setStatus(true);
			response.setData(data);
			response = responseUtils.setMessages(response, "Success " + user.getUserName() + " has been added!",
					"UserController", true);
		} catch (Exception e) {
			throw new ResourceNotFoundException("Something went wrong! Please, check the typed information" + e.getMessage());
		}

		return ResponseEntity.ok(response);
	}

	@PostMapping(value = "/api/user/update")
	public ResponseEntity<Response<UserEntity>> update(@Valid @RequestBody UserEntity user) {
		Response<UserEntity> response = new Response<UserEntity>();

		try {
			service.update(user);
			response.setData(user);
			response = responseUtils.setMessages(response, "Success " + user.getUserName() + " has been updated!",
					"UserController", true);
		} catch (Exception e) {
			throw new ResourceNotFoundException("Something went wrong! Please, check the typed information" + e.getMessage());
		}

		return ResponseEntity.ok(response);
	}

	@PostMapping(value = "/api/user/login")
	public ResponseEntity<Response<Data>> login(@Valid @RequestBody UserEntity user) {
		Response<Data> response = new Response<Data>();
		UserEntity userFromDB = new UserEntity();
		Data data = new Data();

		try {
			userFromDB = service.findByUserName(user.getUserName());
			if (userFromDB != null && userFromDB.getPassword().equals(user.getPassword())) {
				user = userFromDB;
				user.setIsLogged(true);
				update(user);
				data.setId(userFromDB.getId());
				data.setStatus(true);
				response.setData(data);
				response = responseUtils.setMessages(response, "Logged in", "UserController", true);
			} else {
				response = responseUtils.setMessages(response, "Sorry, user login does not match", "UserController",
						false);
			}
		} catch (Exception e) {
			throw new ResourceNotFoundException("Something went wrong! " + e.getMessage());
		}

		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "/api/user/logout/{id}")
	public ResponseEntity<Response<UserEntity>> logout(@PathVariable(name = "id") Long id) {
		Response<UserEntity> response = new Response<UserEntity>();
		UserEntity user = new UserEntity();

		try {
			user = service.findUserById(id).orElse(null);
			if (user != null) {
				user.setIsLogged(false);
				user = service.update(user);
				response.setData(user);
				response = responseUtils.setMessages(response, "User logged out", "UserController", true);
			} else {
				response = responseUtils.setMessages(response, "User not found", "UserController", false);
			}
		} catch (Exception e) {
			throw new ResourceNotFoundException("Something went wrong! User not found" + e.getMessage());
		}

		return ResponseEntity.ok(response);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@GetMapping(value = "/api/user/isLogged/{id}")
	public ResponseEntity<Response<Data>> isLogged(@PathVariable(name = "id") Long id) {
		Response<Data> response = new Response<Data>();
		UserEntity userFromDB = new UserEntity();
		Data data = new Data();

		try {
			userFromDB = service.findUserById(id).orElse(null);
			if (userFromDB != null && userFromDB.getIsLogged().equals(true)) {
				data.setId(userFromDB.getId());
				data.setStatus(true);
				response.setData(data);
				response = responseUtils.setMessages(response, "User is logged", "UserController", true);
			} else {
				response = responseUtils.setMessages(response, "User is not logged", "UserController", false);
			}
		} catch (Exception e) {
			throw new ResourceNotFoundException("Something went wrong! " + e.getMessage());
		}

		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "/api/user/getUserById/{id}")
	public ResponseEntity<Response<UserEntity>> getUserById(@PathVariable("id") Long id) {
		Response<UserEntity> response = new Response<UserEntity>();
		UserEntity userFromDB = new UserEntity();

		try {
			userFromDB = service.findUserById(id).orElse(null);
			if (userFromDB != null) {
				response.setData(userFromDB);
				response = responseUtils.setMessages(response, "User " + userFromDB.getUserName() + " has been found",
						"UserController", true);
			} else {
				response = responseUtils.setMessages(response, "User not found", "UserController", false);
			}
		} catch (Exception e) {
			throw new ResourceNotFoundException("Something went wrong! User not found" + e.getMessage());
		}

		return ResponseEntity.ok(response);
	}

	@PostMapping(value = "/api/user/getUserByEmail")
	public ResponseEntity<Response<UserEntity>> getUserByEmail(@Valid @RequestBody UserEntity user) {
		Response<UserEntity> response = new Response<UserEntity>();
		UserEntity userFromDB = new UserEntity();

		try {
			userFromDB = service.findUserByEmail(user.getEmail());
			if (!userFromDB.getUserName().isEmpty()) {
				response.setData(userFromDB);
				response.setMessage("User " + userFromDB.getUserName() + " has been found");
				response = responseUtils.setMessages(response, "User " + userFromDB.getUserName() + " has been found",
						"UserController", true);
			} else {
				response = responseUtils.setMessages(response, "User not found", "UserController", false);
			}
		} catch (Exception e) {
			throw new ResourceNotFoundException("Something went wrong! E-mail not found");
		}

		return ResponseEntity.ok(response);
	}

	@PostMapping(value = "/api/user/getUser")
	public ResponseEntity<Response<UserEntity>> getUser(@Valid @RequestBody UserEntity user) {
		Response<UserEntity> response = new Response<UserEntity>();
		UserEntity userFromDB = new UserEntity();

		try {
			userFromDB = service.findByUserName(user.getUserName());
			if (!userFromDB.getUserName().isEmpty()) {
				userFromDB.setPassword("");
				response.setData(userFromDB);
				response = responseUtils.setMessages(response, "User " + userFromDB.getUserName() + " has been found",
						"UserController", true);
			} else {
				response = responseUtils.setMessages(response, "User not found", "UserController", false);
			}
		} catch (Exception e) {
			throw new ResourceNotFoundException("Something went wrong! User not found" + e.getMessage());
		}

		return ResponseEntity.ok(response);
	}

	@PostMapping(value = "/api/user/updatePassword")
	public ResponseEntity<Response<UserEntity>> updatePassword(@Valid @RequestBody UserEntity user) {
		Response<UserEntity> response = new Response<UserEntity>();
		UserEntity userFromDB = new UserEntity();

		try {
			userFromDB = service.findUserById(user.getId()).orElse(null);
			if (userFromDB != null) {
				userFromDB.setPassword(user.getPassword());
				service.save(userFromDB);
				response.setData(userFromDB);
				response = responseUtils.setMessages(response, "Password has been changed", "UserController", true);
			} else {
				response = responseUtils.setMessages(response, "User not found", "UserController", false);
			}
		} catch (Exception e) {
			throw new ResourceNotFoundException("Something went wrong! User not found" + e.getMessage());
		}

		return ResponseEntity.ok(response);
	}

}
