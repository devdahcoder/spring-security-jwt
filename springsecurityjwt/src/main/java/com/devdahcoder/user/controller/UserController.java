package com.devdahcoder.user.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.devdahcoder.user.service.UserService;
import org.springframework.web.bind.annotation.*;
import com.devdahcoder.user.model.UserCreateModel;
import com.devdahcoder.user.model.UserResponseModel;
import com.devdahcoder.user.generic.UserGenericListResponse;
import org.springframework.beans.factory.annotation.Autowired;

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
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<UserGenericListResponse<UserResponseModel>> findAllUsers(
			@RequestParam(defaultValue = "10", required = false, name = "limit", value = "limit") int limit,
			@RequestParam(defaultValue = "0", required = false, name = "offset", value = "offset") int offset,
			@RequestParam(defaultValue = "ASC", required = false, name = "order", value = "order") String order
	) {

		List<UserResponseModel> users = userService.findAllUsers(limit, offset, order);

		return new ResponseEntity<>(
				new UserGenericListResponse<>(
						0, offset, order, users, limit, userService.countUser(),
						(int) Math.ceil((double) userService.countUser() / users.size()), false, false, users.size()
				), HttpStatus.OK);

	}

	@GetMapping("/{id}")
	@ResponseStatus(code = HttpStatus.FOUND)
	public ResponseEntity<UserResponseModel> findUserById(@PathVariable int id) {

		return new ResponseEntity<>(userService.findUserById(id), HttpStatus.FOUND);

	}

	@PostMapping("/signup")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<String> createUser(@RequestBody @Valid UserCreateModel userCreateModel) {

		return new ResponseEntity<>(userService.createUser(userCreateModel), HttpStatus.CREATED);

	}

}
