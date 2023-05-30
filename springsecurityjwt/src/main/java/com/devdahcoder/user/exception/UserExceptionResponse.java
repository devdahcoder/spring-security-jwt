package com.devdahcoder.user.exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public record UserExceptionResponse(String message, HttpStatus httpStatus, int statusCode, ZonedDateTime zonedDateTime) {}
