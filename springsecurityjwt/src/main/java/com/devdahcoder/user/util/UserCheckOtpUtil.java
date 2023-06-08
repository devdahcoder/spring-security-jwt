package com.devdahcoder.user.util;

import com.devdahcoder.otp.model.OtpModel;
import com.devdahcoder.otp.repository.OtpRepository;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserCheckOtpUtil {

    private final OtpRepository otpRepository;

    public UserCheckOtpUtil(OtpRepository otpRepository) {

        this.otpRepository = otpRepository;

    }

    public boolean checkUserOtp(OtpModel otpModel) {

        boolean otpExist = otpRepository.otpExist(otpModel.getUsername());

        if (otpExist) {

            OtpModel userOtpModel = new OtpModel();

            if (otpModel.getCode().equals(userOtpModel.getCode())) {

                return true;

            }

        }

        return false;

    }

}
