package com.devdahcoder.user.repository;

import com.devdahcoder.user.contract.UserDetailsContract;
import com.devdahcoder.user.contract.UserDetailsManagerContract;
import com.devdahcoder.user.exception.UserNotFoundException;
import com.devdahcoder.user.extractor.UserResponseExtractor;
import com.devdahcoder.user.mapper.UserResponseRowMapper;
import com.devdahcoder.user.mapper.UserRowMapper;
import com.devdahcoder.user.model.UserCreateModel;
import com.devdahcoder.user.model.UserDetailsModel;
import com.devdahcoder.user.model.UserModel;
import com.devdahcoder.user.model.UserResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository implements UserDetailsService, UserDetailsManagerContract {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public UserRepository(JdbcTemplate jdbcTemplate) {

		this.jdbcTemplate = jdbcTemplate;

	}

	@Override
	public UserDetailsContract loadUserByUsername(String username) throws UsernameNotFoundException {

		String sqlQuery = "select * from user where username = ?";

		UserModel user = jdbcTemplate.queryForObject(sqlQuery, new UserRowMapper(), username);

		if (user == null) {

			throw new UsernameNotFoundException("User not found");

		}

		return new UserDetailsModel(user);

	}


	@Override
	public List<UserResponseModel> findAllUsers() {

		String sqlQuery = "select * from school.user";

		return jdbcTemplate.query(sqlQuery, new UserResponseExtractor());

	}

	@Override
	public String createUser(UserCreateModel userCreateModel) {

		String sqlQuery = "insert into school.user (firstName, lastName, email, username, password) values (?, ?, ?, ?, ?)";

		Object[] objects = { userCreateModel.getFirstName(), userCreateModel.getLastName(), userCreateModel.getEmail(), userCreateModel.getUsername(), userCreateModel.getPassword()};

		int result = jdbcTemplate.update(sqlQuery, objects);

		return result == 1 ? userCreateModel.getUsername() : "Not created";

	}

	@Override
	public UserResponseModel findUserById(long id) {

		String sqlQuery = "select * from user where id = ?";

		return jdbcTemplate.queryForObject(sqlQuery, new UserResponseRowMapper(), id);

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
