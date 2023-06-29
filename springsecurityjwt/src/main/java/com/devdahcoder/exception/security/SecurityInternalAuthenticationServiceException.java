package com.devdahcoder.exception.security;

import org.springframework.security.authentication.InternalAuthenticationServiceException;

public class SecurityInternalAuthenticationServiceException extends InternalAuthenticationServiceException {

    public SecurityInternalAuthenticationServiceException(String message, Throwable cause) {

        super(message, cause);

    }

    public SecurityInternalAuthenticationServiceException(String message) {

        super(message);

    }

}
