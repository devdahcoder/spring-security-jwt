package com.devdahcoder.exception.security;

import org.springframework.security.core.AuthenticationException;

public class SecurityAuthenticationException extends AuthenticationException {

    public SecurityAuthenticationException(String msg, Throwable cause) {

        super(msg, cause);

    }

    public SecurityAuthenticationException(String msg) {

        super(msg);

    }

}
