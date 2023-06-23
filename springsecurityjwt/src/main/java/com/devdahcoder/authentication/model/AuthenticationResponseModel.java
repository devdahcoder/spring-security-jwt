package com.devdahcoder.authentication.model;

import java.util.Objects;

public class AuthenticationResponseModel {

    private String jwtToken;

    public AuthenticationResponseModel(String jwtToken) {

        this.jwtToken = jwtToken;

    }

    public String getJwtToken() {

        return jwtToken;

    }

    public void setJwtToken(String jwtToken) {

        this.jwtToken = jwtToken;

    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (!(o instanceof AuthenticationResponseModel that)) return false;

        return Objects.equals(getJwtToken(), that.getJwtToken());

    }

    @Override
    public int hashCode() {

        return Objects.hash(getJwtToken());

    }

    @Override
    public String toString() {

        return "AuthenticationResponseModel{" +
                "jwtToken='" + jwtToken + '\'' +
                '}';

    }

}
