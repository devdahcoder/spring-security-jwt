package com.devdahcoder.otp.controller;

import com.devdahcoder.otp.model.OtpResponseModel;
import com.devdahcoder.otp.service.OtpService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user/otp")
public class OtpController {

	private final OtpService otpService;

	public OtpController(OtpService otpService) {

		this.otpService = otpService;

	}

	@GetMapping
	public ResponseEntity<OtpResponseModel> findOtpByUsername(String username) {

		return new ResponseEntity<>(otpService.findOtpByUsername(username), HttpStatus.FOUND);

	}

}
