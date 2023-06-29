package com.devdahcoder.user.contract;

import com.devdahcoder.user.model.UserCreateModel;
import com.devdahcoder.user.model.UserResponseModel;

import java.sql.SQLException;
import java.util.List;

public interface UserDetailsManagerContract {

	public List<UserResponseModel> findAllUsers(int limit, int offset, String order);

	public String createUser(UserCreateModel userCreateModel) throws SQLException;

	public UserResponseModel findUserByUsername(String username) throws SQLException;

	public boolean userExists(String username);

	public int countUser();

}
