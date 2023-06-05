package com.devdahcoder.otp.repository;

import com.devdahcoder.exception.api.ApiException;
import com.devdahcoder.otp.contract.OtpContract;
import com.devdahcoder.otp.mapper.OtpRowMapper;
import com.devdahcoder.otp.model.OtpResponseModel;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OtpRepository implements OtpContract {

	private final JdbcTemplate jdbcTemplate;

	public OtpRepository(JdbcTemplate jdbcTemplate) {

		this.jdbcTemplate = jdbcTemplate;

	}

	@Override
	public OtpResponseModel findOtpByUsername(String username) {

		try {

			String sqlQuery = "select * from school.user where username = ?";

			return jdbcTemplate.queryForObject(sqlQuery, new OtpRowMapper(), username);

		} catch (DataAccessException ex) {

			throw new ApiException("Something went wrong while retrieving your user data");

		}

	}

}
