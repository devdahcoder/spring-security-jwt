package com.devdahcoder.user.contract;

import com.devdahcoder.authentication.model.AuthenticationResponseModel;
import com.devdahcoder.user.model.UserCreateModel;
import com.devdahcoder.authentication.model.AuthenticateUserModel;
import com.devdahcoder.user.model.UserResponseModel;

import java.sql.SQLException;
import java.util.List;

public interface UserDetailsManagerContract {

	public List<UserResponseModel> findAllUsers();

	public String createUser(UserCreateModel userCreateModel) throws SQLException;

	public UserResponseModel findUserByUsername(String username) throws SQLException;

	public boolean userExists(String username);

}
