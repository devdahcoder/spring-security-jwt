package com.devdahcoder.configuration.security.authentication;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;

import java.util.Collection;

public class UsernamePasswordAuthentication extends AbstractAuthenticationToken {

    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;
    private final Object principal;
    private final Object credentials;

    public UsernamePasswordAuthentication(Object principal, Object credentials) {

        super(null);
        this.principal = principal;
        this.credentials = credentials;
        setAuthenticated(false);

    }

    public UsernamePasswordAuthentication(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {

        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        super.setAuthenticated(true);

    }

    public UsernamePasswordAuthentication unAuthenticate(Object principal, Object credentials) {

        return new UsernamePasswordAuthentication(principal, credentials);

    }

    public UsernamePasswordAuthentication authenticate(Object principal, Object credentials,  Collection<? extends GrantedAuthority> authorities) {

        return new UsernamePasswordAuthentication(principal, credentials, authorities);

    }

    @Override
    public Object getCredentials() {

        return this.credentials;

    }

    @Override
    public Object getPrincipal() {

        return this.principal;

    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

        if (isAuthenticated) {

            throw new IllegalArgumentException("Cannot set this token to trusted - use the constructor which takes a GrantedAuthority list instead.");

        }

        super.setAuthenticated(false);

    }

}
