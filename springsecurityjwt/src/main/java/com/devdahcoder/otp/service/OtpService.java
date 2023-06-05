package com.devdahcoder.otp.service;

import com.devdahcoder.otp.contract.OtpContract;
import com.devdahcoder.otp.model.OtpResponseModel;
import com.devdahcoder.otp.repository.OtpRepository;
import org.springframework.stereotype.Service;

@Service
public class OtpService implements OtpContract {

	private final OtpRepository otpRepository;

	public OtpService(OtpRepository otpRepository) {

		this.otpRepository = otpRepository;

	}

	@Override
	public OtpResponseModel findOtpByUsername(String username) {

		return otpRepository.findOtpByUsername(username);

	}

}
