package com.devdahcoder.user.service;

import com.devdahcoder.user.contract.UserDetailsManagerContract;
import com.devdahcoder.user.exception.UserException;
import com.devdahcoder.user.exception.UserNotFoundException;
import com.devdahcoder.user.model.UserCreateModel;
import com.devdahcoder.user.model.UserResponseModel;
import com.devdahcoder.user.repository.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsManagerContract {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {

		this.userRepository = userRepository;

		this.passwordEncoder = passwordEncoder;

	}

	@Override
	public List<UserResponseModel> findAllUsers() {

		return userRepository.findAllUsers();

	}

	public UserResponseModel findUserById(long id) {

		return userRepository.findUserById(id);

	}

	@Override
	public UserResponseModel findUserByUsername(String username) {

		return userRepository.findUserByUsername(username);

	}

	@Override
	public String createUser(@NotNull UserCreateModel userCreateModel) {

		userCreateModel.setPassword(passwordEncoder.encode(userCreateModel.getPassword()));

		userRepository.userExists(userCreateModel.getUsername());

		return userRepository.createUser(userCreateModel);

	}

	public void updateUser(UserDetails user) {

	}

	public void deleteUser(String username) {

	}

	public void changePassword(String oldPassword, String newPassword) {

	}

	public boolean userExists(String username) {
		return false;
	}

}
