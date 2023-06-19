package com.devdahcoder.user.service;

import com.devdahcoder.exception.api.ApiAlreadyExistException;
import com.devdahcoder.jwt.service.JwtService;
import com.devdahcoder.otp.repository.OtpRepository;
import com.devdahcoder.user.contract.UserDetailsContract;
import com.devdahcoder.user.contract.UserDetailsManagerContract;
import com.devdahcoder.user.contract.UserRole;
import com.devdahcoder.user.model.UserAuthenticationResponseModel;
import com.devdahcoder.user.model.UserCreateModel;
import com.devdahcoder.user.model.UserResponseModel;
import com.devdahcoder.user.repository.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsManagerContract {

	private final Logger logger = LoggerFactory.getLogger(UserService.class);
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final OtpRepository otpRepository;
	private final JwtService jwtService;

	@Autowired
	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, OtpRepository otpRepository, JwtService jwtService) {

		this.userRepository = userRepository;

		this.passwordEncoder = passwordEncoder;

		this.otpRepository = otpRepository;

		this.jwtService = jwtService;

	}

	@Override
	public UserDetailsContract loadUserByUsername(String username) {

		logger.info("Service: loading user by username: {}", username);

		return userRepository.loadUserByUsername(username);

	}

	@Override
	public List<UserResponseModel> findAllUsers() {

		logger.info("Service: finding all users");

		return userRepository.findAllUsers();

	}

	public UserResponseModel findUserById(int id) {

		logger.info("Service: finding user by id");

		return userRepository.findUserById(id);

	}

	@Override
	public UserResponseModel findUserByUsername(String username) {

		logger.info("Service: finding user by username");

		return userRepository.findUserByUsername(username);

	}

	@Override
	public UserAuthenticationResponseModel createUser(@NotNull UserCreateModel userCreateModel) {

		logger.info("Service: Creating a new user {}", userCreateModel.getEmail());

		//*
		// Generates a new id for everytime this method gets called
		// */
		userCreateModel.generateUserId();

		//*
		// Encodes the password before saving it to the database
		// */
		userCreateModel.setPassword(passwordEncoder.encode(userCreateModel.getPassword()));

		//*
        // Sets the default role to user
        // */
		userCreateModel.setRole(UserRole.USER);

		logger.info("This is the user role {}", userCreateModel.getRole());

		logger.info("Service: Checking if user {} exist", userCreateModel.getEmail());

		if (userRepository.userExists(userCreateModel.getUsername())) {

			throw new ApiAlreadyExistException("User Already exist");

		}

		userRepository.createUser(userCreateModel);

		var jwtToken = jwtService.generateJwtToken(userCreateModel);

		return new UserAuthenticationResponseModel(jwtToken);

	}

	public void updateUser(UserDetails user) {

	}

	public void deleteUser(String username) {

	}

	public void changePassword(String oldPassword, String newPassword) {

	}

	@Override
	public boolean userExists(String username) {

		logger.info("Service: Checking if user {} existed", username);

		return userRepository.userExists(username);

	}

}
