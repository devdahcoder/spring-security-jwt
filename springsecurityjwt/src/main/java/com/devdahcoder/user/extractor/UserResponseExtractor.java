package com.devdahcoder.user.extractor;

import com.devdahcoder.user.model.UserResponseModel;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserResponseExtractor implements ResultSetExtractor<List<UserResponseModel>> {

	@Override
	public List<UserResponseModel> extractData(ResultSet resultSet) throws SQLException, DataAccessException {

		List<UserResponseModel> userResponseModelList = new ArrayList<>();

		while(resultSet.next()) {

			UserResponseModel userResponseModel = new UserResponseModel();

			userResponseModel.setId(resultSet.getLong("id"));
			userResponseModel.setUserId(resultSet.getString("userId"));
			userResponseModel.setFirstName(resultSet.getString("firstName"));
			userResponseModel.setLastName(resultSet.getString("lastName"));
			userResponseModel.setEmail(resultSet.getString("email"));
			userResponseModel.setUsername(resultSet.getString("username"));
			userResponseModel.setRole(resultSet.getString("role"));

			userResponseModelList.add(userResponseModel);

		}

		return userResponseModelList;

	}

}
