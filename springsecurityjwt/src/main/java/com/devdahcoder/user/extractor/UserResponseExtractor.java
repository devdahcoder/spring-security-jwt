package com.devdahcoder.user.extractor;

import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;

import org.jetbrains.annotations.NotNull;
import org.springframework.dao.DataAccessException;
import com.devdahcoder.user.model.UserQueryModel;
import org.springframework.jdbc.core.ResultSetExtractor;

public class UserResponseExtractor implements ResultSetExtractor<List<UserQueryModel>> {

	@Override
	public List<UserQueryModel> extractData(@NotNull ResultSet resultSet) throws SQLException, DataAccessException {

		List<UserQueryModel> userQueryModelList = new ArrayList<>();

		while(resultSet.next()) {

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

			userQueryModelList.add(userQueryModel);

		}

		return userQueryModelList;

	}

}
