package com.devdahcoder.authentication.contract;

import com.devdahcoder.authentication.model.AuthenticateUserModel;
import com.devdahcoder.authentication.model.AuthenticationResponseModel;
import com.devdahcoder.user.contract.UserDetailsContract;

public interface AuthenticationContract {

    public AuthenticationResponseModel authenticateUser(AuthenticateUserModel authenticateUserModel);

}
