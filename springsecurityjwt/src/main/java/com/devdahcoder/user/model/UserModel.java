package com.devdahcoder.user.model;

import com.devdahcoder.user.contract.UserDetailsContract;
import com.devdahcoder.user.contract.UserRole;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class UserModel implements UserDetailsContract {

	private Long id;
	private UUID userId;
	private String firstName;
	private String lastName;
	private String username;
	private String email;
	private String pwdAlgo;
	private String password;
	private UserRole role;
	private String gender;
	private Date createdAt;
	private List<GrantedAuthority> authorities;

	public UserModel() {}

	public UserModel(
			Long id,
			UUID userId,
			String firstName,
			String lastName,
			String username,
			String email,
			String pwdAlgo,
			String password,
			UserRole role,
			String gender,
			Date createdAt,
			List<GrantedAuthority> authorities
	) {

        this.id = id;
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.pwdAlgo = pwdAlgo;
        this.password = password;
        this.role = UserRole.valueOf(role.toString());
		this.gender = gender;
		this.createdAt = createdAt;
        this.authorities = authorities;

    }

	public Long getId() {

		return id;

	}

	public void setId(Long id) {

		this.id = id;

	}

	public UUID getUserId() {

		return userId;

	}

	public void setUserId(UUID userId) {

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

	@Override
	public String getUsername() {

		return this.username;

	}

	public void setUsername(String username) {

		this.username = username;

	}

	@Override
	public String getEmail() {

		return email;

	}

	public void setEmail(String email) {

		this.email = email;

	}

	public String getPwdAlgo() {

		return pwdAlgo;

	}

	public void setPwdAlgo(String pwdAlgo) {

		this.pwdAlgo = pwdAlgo;

	}

	@Override
	public String getPassword() {

		return this.password;

	}

	public void setPassword(String password) {

		this.password = password;

	}

	public String getRole() {

		return role.name();

	}

	public void setRole(@NotNull UserRole role) {

		this.role = UserRole.valueOf(role.toString());

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

	public void setAuthorities(List<GrantedAuthority> authorities) {

		this.authorities = authorities;

	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return List.of(new SimpleGrantedAuthority(this.getRole()));

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

}
