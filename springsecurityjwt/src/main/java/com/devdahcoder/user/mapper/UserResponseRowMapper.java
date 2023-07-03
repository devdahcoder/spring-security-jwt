package com.devdahcoder.user.mapper;

import com.devdahcoder.user.model.UserQueryModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserResponseRowMapper implements RowMapper<UserQueryModel> {

	@Override
	public UserQueryModel mapRow(ResultSet resultSet, int rowNum) throws SQLException {

		UserQueryModel userQueryModel = new UserQueryModel();

		userQueryModel.setId(resultSet.getLong("id"));
		userQueryModel.setUserId(resultSet.getString("userId"));
		userQueryModel.setFirstName(resultSet.getString("firstName"));
		userQueryModel.setLastName(resultSet.getString("lastName"));
		userQueryModel.setEmail(resultSet.getString("email"));
		userQueryModel.setUsername(resultSet.getString("username"));
		userQueryModel.setRole(resultSet.getString("role"));
		userQueryModel.setGender(resultSet.getString("gender"));
		userQueryModel.setCreatedAt(resultSet.getDate("createdAt"));

		return userQueryModel;

	}
}
