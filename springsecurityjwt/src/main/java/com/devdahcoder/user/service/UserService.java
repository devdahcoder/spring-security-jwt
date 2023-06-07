package com.devdahcoder.user.service;

import com.devdahcoder.exception.api.ApiAlreadyExistException;
import com.devdahcoder.otp.model.OtpModel;
import com.devdahcoder.otp.repository.OtpRepository;
import com.devdahcoder.otp.util.GenerateOtpUtil;
import com.devdahcoder.user.contract.UserDetailsContract;
import com.devdahcoder.user.contract.UserDetailsManagerContract;
import com.devdahcoder.user.model.UserCreateModel;
import com.devdahcoder.user.model.UserResponseModel;
import com.devdahcoder.user.repository.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsManagerContract {

	private final Logger logger = LoggerFactory.getLogger(UserService.class);
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final OtpRepository otpRepository;

	@Autowired
	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, OtpRepository otpRepository) {

		this.userRepository = userRepository;

		this.passwordEncoder = passwordEncoder;

		this.otpRepository = otpRepository;

	}

	@Override
	public List<UserResponseModel> findAllUsers() {

		logger.info("Service: finding all users");

		return userRepository.findAllUsers();

	}

	public UserResponseModel findUserById(long id) {

		logger.info("Service: finding user by id");

		return userRepository.findUserById(id);

	}

	@Override
	public UserResponseModel findUserByUsername(String username) {

		logger.info("Service: finding user by username");

		return userRepository.findUserByUsername(username);

	}

	@Override
	public String createUser(@NotNull UserCreateModel userCreateModel) {

		logger.info("Service: creating a new user");

		userCreateModel.setPassword(passwordEncoder.encode(userCreateModel.getPassword()));

		userCreateModel.generateUserId();

		boolean userExist = userRepository.userExists(userCreateModel.getUsername());

		if (userExist) {

			throw new ApiAlreadyExistException("User Already exist");

		}

		return userRepository.createUser(userCreateModel);

	}

//	private void userOtpAuthentication(@NotNull UserCreateModel userCreateModel) {
//
//		UserDetailsContract userDetailsContract = userRepository.loadUserByUsername(userCreateModel.getUsername());
//
//		if (userDetailsContract == null || !passwordEncoder.matches(userCreateModel.getPassword(), userDetailsContract.getPassword())) {
//
//			throw new BadCredentialsException("Bad credentials.");
//
//		}
//
//		userRenewOtp(userCreateModel);
//
//	}
//
//	private void userRenewOtp(@NotNull UserCreateModel userCreateModel) {
//
//		OtpModel otpModel = new OtpModel();
//
//		String code = GenerateOtpUtil.generateOtp();
//
//		boolean userOtp = otpRepository.otpExist(userCreateModel.getUsername());
//
//		if (userOtp) {
//
//			otpModel.setCode(code);
//
//		} else {
//
//			otpModel.setUsername(userCreateModel.getUsername());
//
//			otpModel.setCode(code);
//
//			otpRepository.createOtp(otpModel);
//
//		}
//
//	}

	public void updateUser(UserDetails user) {

	}

	public void deleteUser(String username) {

	}

	public void changePassword(String oldPassword, String newPassword) {

	}

	@Override
	public boolean userExists(String username) {

		return userRepository.userExists(username);

	}

}
