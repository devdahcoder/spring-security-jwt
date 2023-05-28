package com.devdahcoder.user.service;

import com.devdahcoder.user.contract.UserDetailsContract;
import com.devdahcoder.user.contract.UserDetailsManagerContract;
import com.devdahcoder.user.exception.UserNotFoundException;
import com.devdahcoder.user.model.UserCreateModel;
import com.devdahcoder.user.model.UserResponseModel;
import com.devdahcoder.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService, UserDetailsManagerContract {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {

		this.userRepository = userRepository;

		this.passwordEncoder = passwordEncoder;

	}

	@Override
	public UserDetailsContract loadUserByUsername(String username) throws UsernameNotFoundException {

		return userRepository.loadUserByUsername(username);

	}

	@Override
	public List<UserResponseModel> findAllUsers() {

		return userRepository.findAllUsers();

	}

	@Override
	public String createUser(UserCreateModel userCreateModel) {

		userCreateModel.setPassword(passwordEncoder.encode(userCreateModel.getPassword()));

		return userRepository.createUser(userCreateModel);

	}

	public UserResponseModel findUserById(long id) throws UserNotFoundException, SQLException {

		return userRepository.findUserById(id);

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
