package com.devdahcoder.user.model;

import java.sql.Date;

public class UserResponseModel {

	private Long id;
	private String userId;
	private String firstName;
	private String lastName;
	private String email;
	private String username;
	private String role;
	private String gender;
	private Date createdAt;

	public UserResponseModel() {}

	public UserResponseModel(
			Long id,
			String userId,
			String firstName,
			String lastName,
			String email,
			String username,
			String role,
			String gender,
			Date createdAt
	) {

		this.id = id;
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.username = username;
		this.role = role;
		this.gender = gender;
		this.createdAt = createdAt;

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

	public String getGender() {

		return gender;

	}

	public void setGender(String gender) {

		this.gender = gender;

	}

	public Date getCreatedAt() {

		return createdAt;

	}

	public void setCreatedAt(Date createdAt) {

		this.createdAt = createdAt;

	}

}
