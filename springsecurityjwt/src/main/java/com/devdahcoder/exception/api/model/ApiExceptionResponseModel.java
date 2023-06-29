package com.devdahcoder.exception.api.model;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
import java.util.Objects;

public class ApiExceptionResponseModel {

    private String message;
    private HttpStatus httpStatus;
    private int statusCode;
    private ZonedDateTime zonedDateTime;
    private String path;

    public ApiExceptionResponseModel(String message, HttpStatus httpStatus, int statusCode, ZonedDateTime zonedDateTime) {

        this.message = message;
        this.httpStatus = httpStatus;
        this.statusCode = statusCode;
        this.zonedDateTime = zonedDateTime;

    }

    public ApiExceptionResponseModel(String message, HttpStatus httpStatus, int statusCode, ZonedDateTime zonedDateTime, String path) {

        this.message = message;
        this.httpStatus = httpStatus;
        this.statusCode = statusCode;
        this.zonedDateTime = zonedDateTime;
        this.path = path;

    }

    public String getMessage() {

        return message;

    }

    public void setMessage(String message) {

        this.message = message;

    }

    public HttpStatus getHttpStatus() {

        return httpStatus;

    }

    public void setHttpStatus(HttpStatus httpStatus) {

        this.httpStatus = httpStatus;

    }

    public int getStatusCode() {

        return statusCode;

    }

    public void setStatusCode(int statusCode) {

        this.statusCode = statusCode;

    }

    public ZonedDateTime getZonedDateTime() {

        return zonedDateTime;

    }

    public void setZonedDateTime(ZonedDateTime zonedDateTime) {

        this.zonedDateTime = zonedDateTime;

    }

    public String getPath() {

        return path;

    }

    public void setPath(String path) {

        this.path = path;

    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (!(o instanceof ApiExceptionResponseModel that)) return false;

        return getStatusCode() == that.getStatusCode() && Objects.equals(getMessage(), that.getMessage()) && getHttpStatus() == that.getHttpStatus() && Objects.equals(getZonedDateTime(), that.getZonedDateTime()) && Objects.equals(getPath(), that.getPath());

    }

    @Override
    public int hashCode() {

        return Objects.hash(getMessage(), getHttpStatus(), getStatusCode(), getZonedDateTime(), getPath());

    }

    @Override
    public String toString() {

        return "ApiExceptionResponseModel{" +
                "message='" + message + '\'' +
                ", httpStatus=" + httpStatus +
                ", statusCode=" + statusCode +
                ", zonedDateTime=" + zonedDateTime +
                ", path='" + path + '\'' +
                '}';

    }

}
