package com.devdahcoder.otp.contract;

import com.devdahcoder.otp.model.OtpResponseModel;

public interface OtpContract {

	public OtpResponseModel findOtpByUsername(String username);

}
