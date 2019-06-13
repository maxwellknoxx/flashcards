package com.maxwell.flashcards.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.maxwell.flashcards.entity.UserEntity;
import com.maxwell.flashcards.model.Data;
import com.maxwell.flashcards.response.Response;
import com.maxwell.flashcards.response.ResponseUtils;
import com.maxwell.flashcards.service.impl.UserServiceImpl;

@CrossOrigin(origins = "*")
@RestController
public class UserController {

	@Autowired
	private UserServiceImpl service;

	ResponseUtils responseUtils = new ResponseUtils();

	@PostMapping(value = "/api/user/save")
	public ResponseEntity<Response<UserEntity>> addUser(@Valid @RequestBody UserEntity user) {
		Response<UserEntity> response = new Response<UserEntity>();

		try {
			service.save(user);
			response.setData(user);
			response = responseUtils.setMessages(response, "Success " + user.getUserName() + " has been added!", true);
		} catch (Exception e) {
			return responseUtils.setExceptionMessage(response, e);
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
					true);
		} catch (Exception e) {
			return responseUtils.setExceptionMessage(response, e);
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
				response = responseUtils.setMessages(response, "Logged in", true);
			} else {
				response = responseUtils.setMessages(response, "Sorry, user login does not match", false);
			}
		} catch (Exception e) {
			return responseUtils.setExceptionMessage(response, e);
		}

		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "/api/user/logout/{id}")
	public ResponseEntity<Response<UserEntity>> logout(@PathVariable(name = "id") Long id) {
		Response<UserEntity> response = new Response<UserEntity>();
		UserEntity user = new UserEntity();
		
		try {
			user = service.findUserById(id).orElse(null);
			if(user != null) {
				user.setIsLogged(false);
				user = service.update(user);
				response.setData(user);
				response = responseUtils.setMessages(response, "User logged out", true);
			} else {
				response = responseUtils.setMessages(response, "User not found", false);
			}
		} catch (Exception e) {
			return responseUtils.setExceptionMessage(response, e);
		}

		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "/api/user/isLogged/{id}")
	public ResponseEntity<Response<UserEntity>> isLogged(@PathVariable(name = "id") Long id) {
		Response<UserEntity> response = new Response<UserEntity>();
		UserEntity userFromDB = new UserEntity();

		try {
			userFromDB = service.findUserById(id).orElse(null);
			if (userFromDB != null && userFromDB.getIsLogged().equals(true)) {
				userFromDB.setPassword("");
				userFromDB.setIsLogged(true);
				response.setStatus(true);
				response.setData(userFromDB);
			} else {
				response = responseUtils.setMessages(response, "User not found", false);
			}
		} catch (Exception e) {
			return responseUtils.setExceptionMessage(response, e);
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
						true);
			} else {
				response = responseUtils.setMessages(response, "User not found", false);
			}
		} catch (Exception e) {
			return responseUtils.setExceptionMessage(response, e);
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
						true);
			} else {
				response = responseUtils.setMessages(response, "User not found", false);
			}
		} catch (Exception e) {
			return responseUtils.setExceptionMessage(response, e);
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
						true);
			} else {
				response = responseUtils.setMessages(response, "User not found", false);
			}
		} catch (Exception e) {
			return responseUtils.setExceptionMessage(response, e);
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
				response = responseUtils.setMessages(response, "Password has been changed", true);
			} else {
				response = responseUtils.setMessages(response, "User not found", false);
			}
		} catch (Exception e) {
			return responseUtils.setExceptionMessage(response, e);
		}

		return ResponseEntity.ok(response);
	}

}
