package com.devdahcoder.exception.api;

import org.springframework.security.core.AuthenticationException;

public class ApiAuthenticationException extends AuthenticationException {

    public ApiAuthenticationException(String msg, Throwable cause) {

        super(msg, cause);

    }

    public ApiAuthenticationException(String msg) {

        super(msg);

    }

}
