package com.devdahcoder.user.repository;

import com.devdahcoder.exception.database.BadSqlQueryException;
import com.devdahcoder.exception.database.DatabaseDuplicateKeyException;
import com.devdahcoder.user.contract.UserDetailsContract;
import com.devdahcoder.user.contract.UserDetailsManagerContract;
import com.devdahcoder.exception.api.ApiException;
import com.devdahcoder.exception.api.ApiNotFoundException;
import com.devdahcoder.user.extractor.UserResponseExtractor;
import com.devdahcoder.user.mapper.UserResponseRowMapper;
import com.devdahcoder.user.mapper.UserRowMapper;
import com.devdahcoder.user.model.UserCreateModel;
import com.devdahcoder.user.model.UserDetailsModel;
import com.devdahcoder.user.model.UserModel;
import com.devdahcoder.user.model.UserResponseModel;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.*;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.IncorrectResultSetColumnCountException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.List;

@Repository
public class UserRepository implements UserDetailsService, UserDetailsManagerContract {

	private final Logger logger = LoggerFactory.getLogger(UserRepository.class);

	private final JdbcTemplate jdbcTemplate;

	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public UserRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {

		this.jdbcTemplate = jdbcTemplate;

		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;

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

			return namedParameterJdbcTemplate.query(sqlQuery, new UserResponseExtractor());

		} catch (BadSqlGrammarException ex) {

			logger.error("Bad SQL grammar exception occurred error executing database query: {}", ex.getSql());

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
	public String createUser(@NotNull UserCreateModel userCreateModel) {

		final String sqlQuery = "INSERT INTO school.user (userId, firstName, lastName, username, email, password) VALUES (:userId, :firstName, :lastName, :username, :email, :password)";

		final String userCreationErrorMessage = "Something went wrong while trying to create a new user";

		final String userDuplicateKeyMessage = "A constraint column in the database cannot be duplicated when creating a new user";

		final String userBadSqlGrammarMessage = "Bad sql grammar, there was an issue with the sql query " + sqlQuery;

		try {

			SqlParameterSource createUserSqlParam = new MapSqlParameterSource()
					.addValue("userId", userCreateModel.getUserId())
					.addValue("firstName", userCreateModel.getFirstName())
					.addValue("lastName", userCreateModel.getLastName())
					.addValue("username", userCreateModel.getUsername())
					.addValue("email", userCreateModel.getEmail())
					.addValue("password", userCreateModel.getPassword());

			int queryResult = namedParameterJdbcTemplate.update(sqlQuery, createUserSqlParam);

			if (queryResult == 1) {

				return userCreateModel.getUsername();

			} else {

				throw new ApiException(userCreationErrorMessage);

			}

		} catch (BadSqlGrammarException ex ) {

			logger.isDebugEnabled();

			logger.error(userBadSqlGrammarMessage);

			throw new BadSqlQueryException("createUser", sqlQuery, ex.getSQLException());

		} catch (DataIntegrityViolationException ex) {

			logger.error(userDuplicateKeyMessage);

			throw new DatabaseDuplicateKeyException(userDuplicateKeyMessage, ex);

		} catch (DataAccessException ex) {

			logger.error(userCreationErrorMessage);

			throw new ApiException(userCreationErrorMessage, ex);

		}

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

			logger.info("Completed user exist query now returning user exist query result");

			return queryResult > 0;

		} catch (DataAccessException ex) {

			logger.error("Something went wrong while trying to check if user exist");

			throw new ApiException("Something went wrong while retrieving user data");

		}

	}

}
