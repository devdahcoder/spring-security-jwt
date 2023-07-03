package com.devdahcoder.user.util;

import com.devdahcoder.otp.model.OtpModel;
import com.devdahcoder.otp.repository.OtpRepository;
import com.devdahcoder.otp.util.GenerateOtpUtil;
import org.jetbrains.annotations.NotNull;
import com.devdahcoder.user.model.UserCreateModel;
import com.devdahcoder.user.repository.UserRepository;
import com.devdahcoder.user.contract.UserDetailsContract;
import com.devdahcoder.exception.api.ApiNotFoundException;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.BadCredentialsException;

@Configuration
public class UserOtpUtil {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final OtpRepository otpRepository;

    @Autowired
    public UserOtpUtil(UserRepository userRepository, PasswordEncoder passwordEncoder, OtpRepository otpRepository) {

        this.userRepository = userRepository;

        this.passwordEncoder = passwordEncoder;

        this.otpRepository = otpRepository;

    }

    public boolean checkUserOtp(@NotNull OtpModel otpModel) {

        boolean otpExist = otpRepository.otpExist(otpModel.getUsername());

        OtpModel userOtpModel = new OtpModel();

        if (otpExist) {

            return otpModel.getCode().equals(userOtpModel.getCode());

        }

        return false;

    }

    public void userRenewOtp(@NotNull UserCreateModel userCreateModel) {

        OtpModel otpModel = new OtpModel();

        String code = GenerateOtpUtil.generateOtp();

        boolean userOtp = otpRepository.otpExist(userCreateModel.getUsername());

        if (userOtp) {

            otpModel.setCode(code);

        } else {

            otpModel.setUsername(userCreateModel.getUsername());

            otpModel.setCode(code);

            otpRepository.createOtp(otpModel);

        }

    }

    private void userOtpAuthentication(@NotNull UserCreateModel userCreateModel) {

        try {

            UserDetailsContract user = userRepository.loadUserByUsername(userCreateModel.getUsername());

            if (!passwordEncoder.matches(userCreateModel.getPassword(), user.getPassword())) {

                throw new BadCredentialsException("Bad credentials provided");

            }

            this.userRenewOtp(userCreateModel);

        } catch (ApiNotFoundException ex) {

            throw new BadCredentialsException("Bad credentials provided.");

        }

    }

}
