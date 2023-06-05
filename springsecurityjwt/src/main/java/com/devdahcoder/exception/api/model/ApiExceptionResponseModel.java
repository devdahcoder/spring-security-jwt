package com.devdahcoder.exception.api.model;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public record ApiExceptionResponseModel(String message, HttpStatus httpStatus, int statusCode, ZonedDateTime zonedDateTime) {}
