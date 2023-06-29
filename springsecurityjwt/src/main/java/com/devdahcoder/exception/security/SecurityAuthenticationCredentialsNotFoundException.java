package com.devdahcoder.exception.security;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;

public class SecurityAuthenticationCredentialsNotFoundException extends AuthenticationCredentialsNotFoundException {

    public SecurityAuthenticationCredentialsNotFoundException(String msg) {

        super(msg);

    }

    public SecurityAuthenticationCredentialsNotFoundException(String msg, Throwable cause) {

        super(msg, cause);

    }

}
