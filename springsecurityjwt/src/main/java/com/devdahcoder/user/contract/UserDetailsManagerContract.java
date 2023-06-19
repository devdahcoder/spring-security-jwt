package com.devdahcoder.user.contract;

import com.devdahcoder.user.model.UserAuthenticationResponseModel;
import com.devdahcoder.user.model.UserCreateModel;
import com.devdahcoder.user.model.UserResponseModel;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UserDetailsManagerContract {

	public UserDetailsContract loadUserByUsername(String username);

	public List<UserResponseModel> findAllUsers();

	public UserAuthenticationResponseModel createUser(UserCreateModel userCreateModel) throws SQLException;

	public UserResponseModel findUserByUsername(String username) throws SQLException;

	public boolean userExists(String username);

}
