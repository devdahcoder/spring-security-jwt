package com.devdahcoder.otp.contract;

import com.devdahcoder.otp.model.OtpModel;
import com.devdahcoder.otp.model.OtpResponseModel;

public interface OtpContract {

	public OtpResponseModel findOtpByUsername(String username);

	public boolean otpExist(String username);

	public String createOtp(OtpModel otpModel);

}
