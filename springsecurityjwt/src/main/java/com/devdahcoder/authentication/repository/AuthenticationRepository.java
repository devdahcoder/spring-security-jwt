package com.devdahcoder.authentication.repository;

import com.devdahcoder.user.contract.UserDetailsContract;
import com.devdahcoder.user.mapper.UserRowMapper;
import com.devdahcoder.user.model.UserDetailsModel;
import com.devdahcoder.user.model.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AuthenticationRepository {

    private final Logger logger = LoggerFactory.getLogger(AuthenticationRepository.class);
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public AuthenticationRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {

        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;

    }

//    @Override
//    public UserDetailsContract loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        String sqlQuery = "select * from school.user where username = :username";
//
//        final SqlParameterSource sqlParameterSource = new MapSqlParameterSource("username", username);
//
//        UserModel user = namedParameterJdbcTemplate.queryForObject(sqlQuery, sqlParameterSource, new UserRowMapper());
//
//        if (user == null) {
//
//            throw new UsernameNotFoundException(String.format("User with username %s not found", username));
//
//        }
//
//        return new UserDetailsModel(user);
//
//    }

}
