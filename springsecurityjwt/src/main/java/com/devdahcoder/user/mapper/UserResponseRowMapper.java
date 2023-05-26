package com.devdahcoder.user.mapper;

import com.devdahcoder.user.model.UserResponseModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserResponseRowMapper implements RowMapper<UserResponseModel> {

	@Override
	public UserResponseModel mapRow(ResultSet resultSet, int rowNum) throws SQLException {

		UserResponseModel userResponseModel = new UserResponseModel();

		userResponseModel.setId(resultSet.getLong("id"));
		userResponseModel.setFirstName(resultSet.getString("firstName"));
		userResponseModel.setLastName(resultSet.getString("lastName"));
		userResponseModel.setEmail(resultSet.getString("email"));
		userResponseModel.setUsername(resultSet.getString("username"));

		return userResponseModel;

	}
}
