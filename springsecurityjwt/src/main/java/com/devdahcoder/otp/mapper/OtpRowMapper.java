package com.devdahcoder.otp.mapper;

import com.devdahcoder.otp.model.OtpResponseModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OtpRowMapper implements RowMapper<OtpResponseModel> {

	@Override
	public OtpResponseModel mapRow(ResultSet resultSet, int rowNum) throws SQLException {

		OtpResponseModel otpResponseModel = new OtpResponseModel();
		otpResponseModel.setId(resultSet.getLong("id"));
		otpResponseModel.setUsername(resultSet.getString("username"));
		otpResponseModel.setCode(resultSet.getString("code"));

		return otpResponseModel;

	}

}
