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

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {

		this.userService = userService;

	}

	@GetMapping
	public ResponseEntity<List<UserResponseModel>> findAllUsers() {

		return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);

	}

	@GetMapping("/{id}")
	public ResponseEntity<UserResponseModel> findUserById(@PathVariable long id) {

		return new ResponseEntity<>(userService.findUserById(id), HttpStatus.FOUND);

	}

	@PostMapping("/signup")
	public ResponseEntity<String> createUser(@RequestBody @Valid UserCreateModel userCreateModel) {

		return new ResponseEntity<>(userService.createUser(userCreateModel), HttpStatus.CREATED);

	}

}
