package com.devdahcoder.user.contract;

import com.devdahcoder.user.model.UserCreateModel;
import com.devdahcoder.user.model.UserQueryModel;
import com.devdahcoder.user.model.UserResponseModel;

import java.sql.SQLException;

public interface UserDetailsManagerContract {

	UserResponseModel<?> findAllUsers(int limit, int offset, String order);

	String createUser(UserCreateModel userCreateModel) throws SQLException;

	UserQueryModel findUserByUsername(String username) throws SQLException;

	boolean userExists(String username);

	int countAllUser();

}
