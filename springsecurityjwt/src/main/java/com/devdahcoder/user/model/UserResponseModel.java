package com.devdahcoder.user.model;

import java.util.Objects;

public class UserResponseModel {

	private Long id;
	private String userId;
	private String firstName;
	private String lastName;
	private String email;
	private String username;
	private String role;

	public UserResponseModel() {}

	public UserResponseModel(Long id, String userId, String firstName, String lastName, String email, String username, String role) {

		this.id = id;
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.username = username;
		this.role = role;

	}

	public Long getId() {

		return id;

	}

	public void setId(Long id) {

		this.id = id;

	}

	public String getUserId() {

		return userId;

	}

	public void setUserId(String userId) {

		this.userId = userId;

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

	public String getRole() {

		return role;

	}

	public void setRole(String role) {

		this.role = role;

	}

	@Override
	public String toString() {

		return "UserResponseModel{" +
				"id=" + id +
				", userId=" + userId +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", email='" + email + '\'' +
				", username='" + username + '\'' +
				", role='" + role + '\'' +
				'}';

	}

	@Override
	public boolean equals(Object o) {

		if (this == o) return true;
		if (!(o instanceof UserResponseModel that)) return false;
		return Objects.equals(getId(), that.getId()) && Objects.equals(getUserId(), that.getUserId()) && Objects.equals(getFirstName(), that.getFirstName()) && Objects.equals(getLastName(), that.getLastName()) && Objects.equals(getEmail(), that.getEmail()) && Objects.equals(getUsername(), that.getUsername()) && Objects.equals(getRole(), that.getRole());

	}

	@Override
	public int hashCode() {

		return Objects.hash(getId(), getUserId(), getFirstName(), getLastName(), getEmail(), getUsername(), getRole());

	}
}
