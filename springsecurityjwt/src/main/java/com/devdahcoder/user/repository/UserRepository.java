package com.devdahcoder.user.repository;

import com.devdahcoder.user.contract.UserDetailsContract;
import com.devdahcoder.user.contract.UserDetailsManagerContract;
import com.devdahcoder.exception.api.ApiAlreadyExistException;
import com.devdahcoder.exception.api.ApiException;
import com.devdahcoder.exception.api.ApiNotFoundException;
import com.devdahcoder.user.extractor.UserResponseExtractor;
import com.devdahcoder.user.mapper.UserResponseRowMapper;
import com.devdahcoder.user.mapper.UserRowMapper;
import com.devdahcoder.user.model.UserCreateModel;
import com.devdahcoder.user.model.UserDetailsModel;
import com.devdahcoder.user.model.UserModel;
import com.devdahcoder.user.model.UserResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.IncorrectResultSetColumnCountException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public class UserRepository implements UserDetailsService, UserDetailsManagerContract {

	private final Logger logger = LoggerFactory.getLogger(UserRepository.class);

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

		try {

			String sqlQuery = "select * from school.user";

			return jdbcTemplate.query(sqlQuery, new UserResponseExtractor());

		} catch (BadSqlGrammarException ex) {

			throw new BadSqlGrammarException("findAllUsers", ex.getSql(), (SQLException) ex.getRootCause());

		} catch (IncorrectResultSizeDataAccessException ex) {

			throw new IncorrectResultSizeDataAccessException(ex.getActualSize(), ex.getExpectedSize());

		} catch (IncorrectResultSetColumnCountException ex) {

			throw new IncorrectResultSetColumnCountException(ex.getExpectedCount(), ex.getActualCount());

		} catch (CannotGetJdbcConnectionException ex) {

			throw new CannotGetJdbcConnectionException("A connection to the database could not be made");

		} catch (DataAccessException ex) {

			logger.error("Something went wrong while retrieving users", ex);

			throw new ApiException("Something went wrong while retrieving users");

		}

	}

	public UserResponseModel findUserById(long id) {

		try {

			String sqlQuery = "select * from school.user where id = ?";

			return jdbcTemplate.queryForObject(sqlQuery, new UserResponseRowMapper(), id);

		} catch (EmptyResultDataAccessException ex) {

			throw new ApiNotFoundException("User not found with id: " + id);

		} catch (DataAccessException ex) {

			throw new ApiException("Something went wrong while retrieving your user data");

		}

	}

	@Override
	public UserResponseModel findUserByUsername(String username) {

		try {

			String sqlQuery = "select * from user where username = ?";

			return jdbcTemplate.queryForObject(sqlQuery, new UserResponseRowMapper(), username);

		} catch (EmptyResultDataAccessException ex) {

			throw new ApiNotFoundException("User not found with username: " + username);

		} catch (DataAccessException ex) {

			throw new ApiException("Something went wrong while retrieving your user data");

		}

	}

	@Override
	public String createUser(UserCreateModel userCreateModel) {

		String sqlQuery = "insert into school.user (firstName, lastName, email, username, password) values (?, ?, ?, ?, ?)";

		Object[] objects = { userCreateModel.getFirstName(), userCreateModel.getLastName(), userCreateModel.getEmail(), userCreateModel.getUsername(), userCreateModel.getPassword()};

		int result = jdbcTemplate.update(sqlQuery, objects);

		return result == 1 ? userCreateModel.getUsername() : "Not created";

	}

	public void updateUser(UserDetails user) {

	}

	public void deleteUser(String username) {

	}

	public void changePassword(String oldPassword, String newPassword) {

	}

	@Override
	public boolean userExists(String username) {

		try {

			String sqlQuery = "select count(*) from school.user where username = ?";

			int queryResult = jdbcTemplate.queryForObject(sqlQuery, new Object[] { username }, Integer.class);

			logger.info("Completed user exist query");

			if (queryResult > 0) {

				throw new ApiAlreadyExistException("User Already exist");

			}

			logger.info("Returning user exist query result");

			return false;

		} catch (DataAccessException ex) {

			logger.error("Something went wrong while trying to check if user exist");

			throw new ApiException("Something went wrong while retrieving user data");

		}

	}

}
