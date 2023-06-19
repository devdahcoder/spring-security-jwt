package com.devdahcoder.user.util;

import com.devdahcoder.otp.model.OtpModel;
import com.devdahcoder.otp.repository.OtpRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserCheckOtpUtil {

    private final OtpRepository otpRepository;

    public UserCheckOtpUtil(OtpRepository otpRepository) {

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

}
