package com.devdahcoder.user.controller;

import com.devdahcoder.user.model.UserCreateModel;
import com.devdahcoder.user.model.UserResponseModel;
import com.devdahcoder.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {

		this.userService = userService;

	}

	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<List<UserResponseModel>> findAllUsers() {

		return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);

	}

	@GetMapping("/{id}")
	@ResponseStatus(code = HttpStatus.FOUND)
	public ResponseEntity<UserResponseModel> findUserById(@PathVariable UUID userId) {

		return new ResponseEntity<>(userService.findUserById(userId), HttpStatus.FOUND);

	}

	@PostMapping("/signup")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<String> createUser(@RequestBody @Valid UserCreateModel userCreateModel) {

		return new ResponseEntity<>(userService.createUser(userCreateModel), HttpStatus.CREATED);

	}

}
