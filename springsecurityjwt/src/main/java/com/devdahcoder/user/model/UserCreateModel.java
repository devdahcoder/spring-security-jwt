package com.devdahcoder.user.model;

import com.devdahcoder.user.contract.UserDetailsContract;
import com.devdahcoder.user.contract.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class UserCreateModel implements UserDetailsContract {

	private UUID userId;
	@NotBlank( message = "Firstname cannot be empty" )
	private String firstName;
	@NotBlank( message = "Lastname cannot be empty" )
	private String lastName;
	@Email( message = "Email must be a  valid email" )
	@NotBlank( message = "Email cannot be empty" )
	private String email;
	@NotBlank( message = "Username cannot be empty" )
	private String username;
	@NotBlank( message = "Password cannot be empty" )
	private String password;
	private UserRole role;

	public UserCreateModel() {}

	public UserCreateModel(String firstName, String lastName, String email, String username, String password, UserRole role) {

		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.username = username;
		this.password = password;
		this.role = role;

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

	public void generateUserId() {

		this.userId = UUID.randomUUID();

	}

	public String getUserId() {

		return userId.toString();

	}

	@Override
	public UserRole getRole() {

		return role;

	}

	public void setRole(UserRole role) {

		this.role = role;

	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return List.of(() -> "write");

	}

	@Override
	public boolean isAccountNonExpired() {

		return true;

	}

	@Override
	public boolean isAccountNonLocked() {

		return true;

	}

	@Override
	public boolean isCredentialsNonExpired() {

		return true;

	}

	@Override
	public boolean isEnabled() {

		return true;

	}

	@Override
	public boolean equals(Object o) {

		if (this == o) return true;

		if (!(o instanceof UserCreateModel that)) return false;

		return Objects.equals(getFirstName(), that.getFirstName()) && Objects.equals(getLastName(), that.getLastName()) && Objects.equals(getEmail(), that.getEmail()) && Objects.equals(getUsername(), that.getUsername()) && Objects.equals(getPassword(), that.getPassword()) && Objects.equals(getRole(), that.getRole());

	}

	@Override
	public int hashCode() {

		return Objects.hash(getFirstName(), getLastName(), getEmail(), getUsername(), getPassword(), getRole());

	}

}
