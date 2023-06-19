package com.devdahcoder.user.model;

import java.util.Objects;

public class UserAuthenticationResponseModel {

    private String jwtToken;

    public UserAuthenticationResponseModel(String jwtToken) {

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

        if (!(o instanceof UserAuthenticationResponseModel that)) return false;

        return Objects.equals(getJwtToken(), that.getJwtToken());

    }

    @Override
    public int hashCode() {

        return Objects.hash(getJwtToken());

    }

    @Override
    public String toString() {

        return "UserAuthenticationResponseModel{" +
                "jwtToken='" + jwtToken + '\'' +
                '}';

    }

}
