package com.devdahcoder.user.service;

import com.devdahcoder.user.contract.UserDetailsManagerContract;
import com.devdahcoder.user.exception.UserException;
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

		try {

			return userRepository.findUserById(id);

		} catch (EmptyResultDataAccessException ex) {

			throw new UserException("User not found with id: " + id);

		} catch (DataAccessException ex) {

			throw new UserException("Something went wrong while retrieving your user data");

		}

	}

	@Override
	public UserResponseModel findUserByUsername(String username) throws EmptyResultDataAccessException, DataAccessException {

		try {

			return userRepository.findUserByUsername(username);

		} catch (EmptyResultDataAccessException ex) {

			throw new UserException("User not found with username: " + username);

		} catch (DataAccessException ex) {

			throw new RuntimeException("Something went wrong while retrieving your user data");

		}

	}

	@Override
	public String createUser(@NotNull UserCreateModel userCreateModel) {

		try {

			userCreateModel.setPassword(passwordEncoder.encode(userCreateModel.getPassword()));

			boolean userExists  = userRepository.userExists(userCreateModel.getUsername());

			if (userExists) {

				throw new UserException("User Already exist");

			}

			return userRepository.createUser(userCreateModel);

		} catch (DataAccessException ex) {

			throw new RuntimeException(ex);

		}

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
