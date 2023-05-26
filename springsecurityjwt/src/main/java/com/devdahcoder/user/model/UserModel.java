package com.devdahcoder.user.model;

import com.devdahcoder.user.contract.UserDetailsContract;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserModel implements UserDetailsContract {

	private Long id;
	private String firstName;
	private String lastName;
	private String username;
	private String email;
	private String pwdAlgo;
	private String password;
	private List<GrantedAuthority> authorities;

	public UserModel() {}

	public UserModel(Long id, String firstName, String lastName, String username, String email, String pwdAlgo, String password) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.email = email;
		this.pwdAlgo = pwdAlgo;
		this.password = password;
	}

	public Long getId() {

		return id;

	}

	public void setId(Long id) {

		this.id = id;

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

	public void setPassword(String password) {

		this.password = password;

	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(() -> "delete");
//		return this.authorities;

	}

	@Override
	public String getPassword() {

		return this.password;

	}

	@Override
	public String getUsername() {

		return this.username;

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
