package com.devdahcoder.user.mapper;

import com.devdahcoder.user.model.UserModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<UserModel> {

	@Override
	public UserModel mapRow(ResultSet rs, int rowNum) throws SQLException {

		UserModel userModel = new UserModel();

		userModel.setId(rs.getLong("id"));
		userModel.setFirstName(rs.getString("firstName"));
		userModel.setLastName((rs.getString("lastName")));
		userModel.setPassword(rs.getString("password"));
		userModel.setEmail(rs.getString("email"));
		userModel.setUsername(rs.getString("username"));

		return userModel;

	}

}