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
import com.maxwell.flashcards.model.User;
import com.maxwell.flashcards.service.impl.MapValidationErrorService;
import com.maxwell.flashcards.service.impl.UserServiceImpl;
import com.maxwell.flashcards.util.PasswordUtils;

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

		entity.setIsLogged(false);

		entity.setPassword(PasswordUtils.base64Encode(entity.getPassword()));
		User user = service.save(entity);
		if (user == null) {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}

		return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
	}

	/**
	 * 
	 * @param entity
	 * @return
	 */
	@PutMapping(value = "/api/v1/user/users")
	public ResponseEntity<?> update(@Valid @RequestBody UserEntity entity) {

		entity.setPassword(PasswordUtils.base64Encode(entity.getPassword()));
		User user = service.update(entity);
		if (user == null) {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}

		return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
	}

	/**
	 * 
	 * @param entity
	 * @return
	 */
	@PostMapping(value = "/api/v1/user/login")
	public ResponseEntity<?> login(@Valid @RequestBody UserEntity entity) {
		User user;

		UserEntity userFromDB = service.getByUserName(entity.getUserName());
		if (userFromDB != null && PasswordUtils.base64Decode(userFromDB.getPassword()).equals(entity.getPassword())) {
			entity = userFromDB;
			entity.setIsLogged(true);
			user = service.update(entity);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(false, HttpStatus.OK);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/api/v1/user/logout/{id}")
	public ResponseEntity<?> logout(@PathVariable(name = "id") Long id) {

		UserEntity user = service.findUserById(id);
		if (user != null) {
			user.setIsLogged(false);
			service.update(user);
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(false, HttpStatus.OK);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@GetMapping(value = "/api/v1/user/isLogged/{id}")
	public ResponseEntity<?> isLogged(@PathVariable(name = "id") Long id) {

		UserEntity userFromDB = service.findUserById(id);
		if (userFromDB != null) {
			if (userFromDB.getIsLogged().equals(true)) {
				return new ResponseEntity<Boolean>(true, HttpStatus.OK);
			}
		}
		return new ResponseEntity<Boolean>(false, HttpStatus.OK);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/api/v1/user/users/{id}")
	public ResponseEntity<?> getUserById(@PathVariable("id") Long id) {

		UserEntity userFromDB = service.findUserById(id);
		if (userFromDB != null) {
			return new ResponseEntity<UserEntity>(userFromDB, HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(false, HttpStatus.OK);
	}

	/**
	 * 
	 * @param entity
	 * @return
	 */
	@PostMapping(value = "/api/v1/user/validations")
	public ResponseEntity<?> validations(@Valid @RequestBody UserEntity entity) {

		UserEntity userFromDB = service.getUserByEmail(entity.getEmail());
		if (userFromDB != null) {
			if (userFromDB.getAnswer().equals(entity.getAnswer())) {
				if (userFromDB.getUserName().equals(entity.getUserName())) {
					userFromDB.setPassword(PasswordUtils.base64Encode(entity.getPassword()));
					service.update(userFromDB);
					return new ResponseEntity<Boolean>(true, HttpStatus.OK);
				}
			}
		}
		return new ResponseEntity<Boolean>(false, HttpStatus.OK);
	}

	/**
	 * 
	 * @param entity
	 * @return
	 */
	@PostMapping(value = "/api/v1/user/getUser")
	public ResponseEntity<?> getUser(@Valid @RequestBody UserEntity entity) {

		User userFromDB = service.findByUserName(entity.getUserName());
		if (userFromDB == null) {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}
		if (userFromDB.getUsername().isEmpty()) {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}

		return new ResponseEntity<User>(userFromDB, HttpStatus.OK);
	}

}
