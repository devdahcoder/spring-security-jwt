package com.devdahcoder.user.repository;

import com.devdahcoder.user.generic.UserGenericListResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.*;
import com.devdahcoder.user.model.*;
import org.jetbrains.annotations.NotNull;
import com.devdahcoder.user.mapper.UserRowMapper;
import com.devdahcoder.exception.api.ApiException;
import org.springframework.jdbc.BadSqlGrammarException;
import com.devdahcoder.user.contract.UserDetailsContract;
import com.devdahcoder.user.mapper.UserResponseRowMapper;
import com.devdahcoder.exception.api.ApiNotFoundException;
import com.devdahcoder.user.extractor.UserResponseExtractor;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import com.devdahcoder.user.contract.UserDetailsManagerContract;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.IncorrectResultSetColumnCountException;
import com.devdahcoder.exception.database.DatabaseTypeMismatchException;
import org.springframework.security.core.userdetails.UserDetailsService;
import com.devdahcoder.exception.database.DatabaseBadSqlGrammarException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.devdahcoder.exception.database.DatabaseIntegrityConstraintViolationException;

import java.util.List;

@Configuration
public class UserRepository implements UserDetailsService, UserDetailsManagerContract {

	private final Logger logger = LoggerFactory.getLogger(UserRepository.class);

	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public UserRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {

		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;

	}

	@Override
	public UserDetailsContract loadUserByUsername(String username) throws UsernameNotFoundException {

		try {

			String sqlQuery = "select * from school.user where username = :username";

			final SqlParameterSource sqlParameterSource = new MapSqlParameterSource("username", username);

			UserModel user = namedParameterJdbcTemplate.queryForObject(sqlQuery, sqlParameterSource, new UserRowMapper());

			if (user == null) {

				throw new UsernameNotFoundException(String.format("User with username %s not found", username));

			}

			return new UserDetailsModel(user);

		} catch (BadSqlGrammarException ex ) {

			logger.error("BadSqlGrammarException: Sql query could not be accessed because because of query errors");

			throw new DatabaseBadSqlGrammarException("loadUserByUsername:", ex.getSql(), ex.getSQLException());

		} catch (EmptyResultDataAccessException ex) {

			throw new UsernameNotFoundException(String.format("User with username %s not found", username), ex);

		}

	}

	@Override
	public List<UserResponseModel> findAllUsers(int limit, int offset, String order) {

		final String sqlQuery = "SELECT * FROM school.user ORDER BY id " + order + " LIMIT :limit OFFSET :offset";

		SqlParameterSource findAllUsersSqlParam = new MapSqlParameterSource()
				.addValue("limit", limit)
				.addValue("offset", offset)
				.addValue("order", order);

		try {

			return namedParameterJdbcTemplate.query(sqlQuery, findAllUsersSqlParam, new UserResponseExtractor());

		} catch (BadSqlGrammarException ex) {

			logger.error("Bad SQL grammar exception occurred error executing database query: {}", ex.getSql());

			throw new DatabaseBadSqlGrammarException("findAllUsers", ex.getSql(), ex.getSQLException());

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

	public UserResponseModel findUserById(int id) {

		final String sqlQuery = "select * from school.user where id = :id";

		final String userBadSqlGrammarMessage = "Bad sql grammar, there was an issue with the sql query " + sqlQuery;

		SqlParameterSource findUserByIdSqlParam = new MapSqlParameterSource("id", id);

		try {

			return namedParameterJdbcTemplate.queryForObject(sqlQuery, findUserByIdSqlParam, new UserResponseRowMapper());

		} catch (BadSqlGrammarException ex ) {

			logger.error(userBadSqlGrammarMessage);

			throw new DatabaseBadSqlGrammarException("findUserById", ex.getSql(), ex.getSQLException());

		} catch (TypeMismatchDataAccessException ex) {

			logger.error("Type mismatch occurred when finding a user with id: " + id + " {}", ex.getMessage());

			throw new DatabaseTypeMismatchException("Error finding user: Invalid data type", ex);

		} catch (EmptyResultDataAccessException ex) {

			logger.error("User not found expected to find {} user but got {}", ex.getExpectedSize(), ex.getActualSize());

			throw new ApiNotFoundException("User not found with id: " + id, ex.getExpectedSize(), ex);

		} catch (DataAccessException ex) {

			throw new ApiException("Something went wrong while retrieving your user data");

		}

	}

	@Override
	public UserResponseModel findUserByUsername(String username) {

		final String sqlQuery = "select * from user where username = :username";

		SqlParameterSource findUserByUsernameSqlParam = new MapSqlParameterSource("username", username);

		try {

			return namedParameterJdbcTemplate.queryForObject(sqlQuery, findUserByUsernameSqlParam, new UserResponseRowMapper());

		} catch (EmptyResultDataAccessException ex) {

			throw new ApiNotFoundException("User not found with username: ", ex.getExpectedSize(), ex);

		} catch (DataAccessException ex) {

			throw new ApiException("Something went wrong while retrieving your user data");

		}

	}

	@Override
	public String createUser(@NotNull UserCreateModel userCreateModel) {

		final String sqlQuery = "INSERT INTO school.user (userId, firstName, lastName, username, email, role, password) VALUES (:userId, :firstName, :lastName, :username, :email, :role, :password)";

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
					.addValue("role", userCreateModel.getRole())
					.addValue("password", userCreateModel.getPassword());

			int queryResult = namedParameterJdbcTemplate.update(sqlQuery, createUserSqlParam);

			if (queryResult == 1) {

				return "Successfully created " + userCreateModel.getUsername();

			} else {

				throw new ApiException(userCreationErrorMessage);

			}

		} catch (BadSqlGrammarException ex ) {

			logger.isDebugEnabled();

			logger.error(userBadSqlGrammarMessage);

			throw new DatabaseBadSqlGrammarException("createUser", ex.getSql(), ex.getSQLException());

		} catch (TypeMismatchDataAccessException ex) {

			logger.error("Type mismatch occurred when creating a user: {}", ex.getMessage());

			throw new DatabaseTypeMismatchException("Error creating user: Invalid data type", ex);

		} catch (DataIntegrityViolationException ex) {

			logger.error(userDuplicateKeyMessage);

			throw new DatabaseIntegrityConstraintViolationException(userDuplicateKeyMessage, ex);

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

		final String sqlQuery = "select count(*) from school.user where username = :username";

		SqlParameterSource sqlParameterSource = new MapSqlParameterSource("username", username);

		try {

			int queryResult = namedParameterJdbcTemplate.queryForObject(sqlQuery, sqlParameterSource, Integer.class);

			logger.info("Completed user exist query now returning user exist query result");

			return queryResult == 1;

		} catch (DataAccessException ex) {

			logger.error("Something went wrong while trying to check if user exist");

			throw new ApiException("Something went wrong while retrieving user data");

		}

	}

	@Override
	public int countUser() {

		final String sqlQuery = "SELECT COUNT(*) FROM school.user";

		return namedParameterJdbcTemplate.queryForObject(sqlQuery, new MapSqlParameterSource(), Integer.class);

	}

}
