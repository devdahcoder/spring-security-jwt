package com.devdahcoder.otp.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public final class GenerateOtpUtil {

    private GenerateOtpUtil() {}

    public static String generateOtp() {

        String code;

        try {

            SecureRandom random = SecureRandom.getInstanceStrong();

            int randomCode = random.nextInt(9000) + 1000;

            code = String.valueOf(randomCode);

        } catch (NoSuchAlgorithmException e) {

            throw new RuntimeException("Problem when generating the random code.");

        }
        return code;

    }

}
