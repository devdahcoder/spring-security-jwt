package com.devdahcoder.user.model;

import java.util.Objects;

public class OtpModel {

	private long id;
	private String username;
	private String code;

	public OtpModel() {
	}

	public OtpModel(long id, String username, String code) {

		this.id = id;

		this.username = username;

		this.code = code;

	}

	public long getId() {

		return id;

	}

	public void setId(long id) {

		this.id = id;

	}

	public String getUsername() {

		return username;

	}

	public void setUsername(String username) {

		this.username = username;

	}

	public String getCode() {

		return code;

	}

	public void setCode(String code) {

		this.code = code;

	}

	@Override
	public boolean equals(Object o) {

		if (this == o) return true;
		if (!(o instanceof OtpModel otpModel)) return false;
		return getId() == otpModel.getId() && Objects.equals(getUsername(), otpModel.getUsername()) && Objects.equals(getCode(), otpModel.getCode());

	}

	@Override
	public int hashCode() {

		return Objects.hash(getId(), getUsername(), getCode());

	}

	@Override
	public String toString() {

		return "OtpModel{" +
				"id=" + id +
				", username='" + username + '\'' +
				", code='" + code + '\'' +
				'}';

	}

}
