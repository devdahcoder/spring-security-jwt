package com.devdahcoder.user.util;

import com.devdahcoder.exception.api.ApiNotFoundException;
import com.devdahcoder.user.contract.UserDetailsContract;
import com.devdahcoder.user.model.UserCreateModel;
import com.devdahcoder.user.repository.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserOtpVerificationUtil {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRenewOtpUtil userRenewOtpUtil;

    @Autowired
    public UserOtpVerificationUtil(UserRepository userRepository, PasswordEncoder passwordEncoder, UserRenewOtpUtil userRenewOtpUtil) {

        this.userRepository = userRepository;

        this.passwordEncoder = passwordEncoder;

        this.userRenewOtpUtil = userRenewOtpUtil;

    }

    private void userOtpAuthentication(@NotNull UserCreateModel userCreateModel) {

        try {

            UserDetailsContract user = userRepository.loadUserByUsername(userCreateModel.getUsername());

            if (!passwordEncoder.matches(userCreateModel.getPassword(), user.getPassword())) {

                throw new BadCredentialsException("Bad credentials provided");

            }

            userRenewOtpUtil.userRenewOtp(userCreateModel);

        } catch (ApiNotFoundException ex) {

            throw new BadCredentialsException("Bad credentials provided.");

        }

    }

}
