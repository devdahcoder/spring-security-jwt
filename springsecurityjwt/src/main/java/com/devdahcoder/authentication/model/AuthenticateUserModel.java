package com.devdahcoder.authentication.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

public class AuthenticateUserModel {

    @NotBlank(message = "Username cannot be empty")
    private String username;

    @NotBlank(message = "Password cannot be empty")
    private String password;

    public AuthenticateUserModel() {};

    public AuthenticateUserModel(String username, String password) {

        this.username = username;

        this.password = password;

    }

    public String getUsername() {

        return username;

    }


    public void setUsername(String username) {

        this.username = username;

    }

    public String getPassword() {

        return password;

    }

    public void setPassword(String password) {

        this.password = password;

    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (!(o instanceof AuthenticateUserModel that)) return false;

        return Objects.equals(getUsername(), that.getUsername()) && Objects.equals(getPassword(), that.getPassword());

    }

    @Override
    public int hashCode() {

        return Objects.hash(getUsername(), getPassword());

    }

    @Override
    public String toString() {

        return "AuthenticateUserModel{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';

    }

}
