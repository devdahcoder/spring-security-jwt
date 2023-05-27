package com.devdahcoder.user.contract;

import com.devdahcoder.user.model.UserCreateModel;
import com.devdahcoder.user.model.UserResponseModel;

import java.util.List;

public interface UserDetailsManagerContract {

	public List<UserResponseModel> findAllUsers();

	public String createUser(UserCreateModel userCreateModel);

	public UserResponseModel findUserById(long id);

}
