package com.devdahcoder.user.mapper;

import com.devdahcoder.user.contract.UserRole;
import com.devdahcoder.user.model.UserModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<UserModel> {

	@Override
	public UserModel mapRow(ResultSet resultSet, int rowNum) throws SQLException {

		UserModel userModel = new UserModel();

		userModel.setId(resultSet.getLong("id"));
		userModel.setFirstName(resultSet.getString("firstName"));
		userModel.setLastName((resultSet.getString("lastName")));
		userModel.setPassword(resultSet.getString("password"));
		userModel.setEmail(resultSet.getString("email"));
		userModel.setUsername(resultSet.getString("username"));
		userModel.setRole(UserRole.valueOf(resultSet.getString("role")));
		userModel.setGender(resultSet.getString("gender"));
		userModel.setCreatedAt(resultSet.getDate("createdAt"));

		return userModel;

	}

}