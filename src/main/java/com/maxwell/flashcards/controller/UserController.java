package com.maxwell.flashcards.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.maxwell.flashcards.entity.UserEntity;
import com.maxwell.flashcards.exception.ResourceNotFoundException;
import com.maxwell.flashcards.model.User;
import com.maxwell.flashcards.service.impl.MapValidationErrorService;
import com.maxwell.flashcards.service.impl.UserServiceImpl;
import com.maxwell.flashcards.util.PasswordUtils;
import com.maxwell.flashcards.util.Utils;

@CrossOrigin(origins = "*")
@RestController
public class UserController {

	@Autowired
	private UserServiceImpl service;

	@Autowired
	private MapValidationErrorService mapValidationErrorService;

	/**
	 * 
	 * @param entity
	 * @return
	 */
	@PostMapping(value = "/api/v1/user/users")
	public ResponseEntity<?> addUser(@Valid @RequestBody UserEntity entity, BindingResult result) {
		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidation(result);
		if (errorMap != null) {
			return errorMap;
		}

		entity.setIsLogged(true);

		entity.setPassword(PasswordUtils.base64Encode(entity.getPassword()));
		User user = Utils.convertUserEntityToModel((service.save(entity)));

		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}

	/**
	 * 
	 * @param entity
	 * @return
	 */
	@PutMapping(value = "/api/v1/user/users")
	public ResponseEntity<?> update(@Valid @RequestBody UserEntity entity) {

		entity.setPassword(PasswordUtils.base64Encode(entity.getPassword()));
		User user = Utils.convertUserEntityToModel(service.update(entity));

		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}

	/**
	 * 
	 * @param entity
	 * @return
	 */
	@PostMapping(value = "/api/v1/user/login")
	public ResponseEntity<?> login(@Valid @RequestBody UserEntity entity) {
		UserEntity userFromDB = new UserEntity();
		User user;

		userFromDB = service.findByUserName(entity.getUserName());
		if (userFromDB != null && PasswordUtils.base64Decode(userFromDB.getPassword()).equals(entity.getPassword())) {
			entity = userFromDB;
			entity.setIsLogged(true);
			user = Utils.convertUserEntityToModel(service.update(entity));
		} else {
			throw new ResourceNotFoundException("Please, verify login information");
		}

		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/api/v1/user/logout/{id}")
	public ResponseEntity<?> logout(@PathVariable(name = "id") Long id) {
		UserEntity user = new UserEntity();

		user = service.findUserById(id);
		if (user != null) {
			user.setIsLogged(false);
			user = service.update(user);
		} else {
			throw new ResourceNotFoundException("User not found");
		}

		return new ResponseEntity<String>("User logged out successfully", HttpStatus.OK);
	}

	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@GetMapping(value = "/api/v1/user/isLogged/{id}")
	public ResponseEntity<?> isLogged(@PathVariable(name = "id") Long id) {
		UserEntity userFromDB = new UserEntity();
		User user;

		userFromDB = service.findUserById(id);
		if (userFromDB != null && userFromDB.getIsLogged().equals(true)) {
			user = Utils.convertUserEntityToModel(userFromDB);
		} else {
			throw new ResourceNotFoundException("User not found");
		}

		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/api/v1/user/getUserById/{id}")
	public ResponseEntity<?> getUserById(@PathVariable("id") Long id) {
		UserEntity userFromDB = new UserEntity();

		userFromDB = service.findUserById(id);
		if (userFromDB != null) {
			return new ResponseEntity<UserEntity>(userFromDB, HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException("User not found");
		}
	}

	/**
	 * 
	 * @param entity
	 * @return
	 */
	@PostMapping(value = "/api/v1/user/getUserByEmail")
	public ResponseEntity<?> getUserByEmail(@Valid @RequestBody UserEntity entity) {

		User userFromDB = Utils.convertUserEntityToModel(service.findUserByEmail(entity.getEmail()));
		if (!userFromDB.getUsername().isEmpty()) {
			return new ResponseEntity<User>(userFromDB, HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException("User not found");
		}
	}

	/**
	 * 
	 * @param entity
	 * @return
	 */
	@PostMapping(value = "/api/v1/user/getUser")
	public ResponseEntity<?> getUser(@Valid @RequestBody UserEntity entity) {

		User userFromDB = Utils.convertUserEntityToModel(service.findByUserName(entity.getUserName()));
		if (!userFromDB.getUsername().isEmpty()) {
			return new ResponseEntity<User>(userFromDB, HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException("User not found");
		}
	}

}
