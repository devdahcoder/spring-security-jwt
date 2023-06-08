package com.devdahcoder.otp.repository;

import com.devdahcoder.exception.api.ApiException;
import com.devdahcoder.otp.contract.OtpContract;
import com.devdahcoder.otp.mapper.OtpRowMapper;
import com.devdahcoder.otp.model.OtpModel;
import com.devdahcoder.otp.model.OtpResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class OtpRepository implements OtpContract {

	private final Logger logger = LoggerFactory.getLogger(OtpRepository.class);

	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public OtpRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate, JdbcTemplate jdbcTemplate) {

		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;

		this.jdbcTemplate = jdbcTemplate;

	}

	@Override
	public OtpResponseModel findOtpByUsername(String username) {

		final String sqlQuery = "select * from school.user where username = username";

		try {

			SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("username", username);

			return namedParameterJdbcTemplate.queryForObject(sqlQuery, sqlParameterSource, new OtpRowMapper());

		} catch (DataAccessException ex) {

			throw new ApiException("Something went wrong while retrieving your user data");

		}

	}

	@Override
	public String createOtp(OtpModel otpModel) {

		String sqlQuery = "insert into school.otp (username, code) values (?, ?)";

		Object[] objects = { otpModel.getUsername(), otpModel.getCode() };

		int queryResult =  jdbcTemplate.update(sqlQuery, objects);

		return queryResult > 0 ? otpModel.getUsername() : "Not created";

	}

	@Override
	public boolean otpExist(String username) {

		try {

			String sqlQuery = "select count(*) from school.user where username = ?";

			int queryResult = jdbcTemplate.queryForObject(sqlQuery, new Object[] { username }, Integer.class);

			return queryResult == 1;

		} catch(DataAccessException ex) {

			throw new ApiException("Something went wrong while retrieving your user data");

		}

	}

}
