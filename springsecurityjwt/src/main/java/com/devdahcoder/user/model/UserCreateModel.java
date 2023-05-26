package com.devdahcoder.user.model;

import java.util.Objects;

public class UserCreateModel {

	private String firstName;
	private String lastName;
	private String email;
	private String username;
	private String password;

	public UserCreateModel() {

	}

	public UserCreateModel(String firstName, String lastName, String email, String username, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.username = username;
		this.password = password;
	}

	public String getFirstName() {

		return firstName;

	}

	public void setFirstName(String firstName) {

		this.firstName = firstName;

	}

	public String getLastName() {

		return lastName;

	}

	public void setLastName(String lastName) {

		this.lastName = lastName;

	}

	public String getEmail() {

		return email;

	}

	public void setEmail(String email) {

		this.email = email;

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
		if (!(o instanceof UserCreateModel that)) return false;
		return Objects.equals(getFirstName(), that.getFirstName()) && Objects.equals(getLastName(), that.getLastName()) && Objects.equals(getEmail(), that.getEmail()) && Objects.equals(getUsername(), that.getUsername()) && Objects.equals(getPassword(), that.getPassword());

	}

	@Override
	public int hashCode() {

		return Objects.hash(getFirstName(), getLastName(), getEmail(), getUsername(), getPassword());

	}
}
