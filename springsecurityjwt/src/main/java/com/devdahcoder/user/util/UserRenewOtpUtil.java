package com.devdahcoder.user.util;

import com.devdahcoder.otp.model.OtpModel;
import com.devdahcoder.otp.repository.OtpRepository;
import com.devdahcoder.otp.util.GenerateOtpUtil;
import com.devdahcoder.user.model.UserCreateModel;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserRenewOtpUtil {

    private final OtpRepository otpRepository;

    @Autowired
    public UserRenewOtpUtil(OtpRepository otpRepository) {

        this.otpRepository = otpRepository;

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

}
