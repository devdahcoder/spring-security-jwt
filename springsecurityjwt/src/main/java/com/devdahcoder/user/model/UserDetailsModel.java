package com.devdahcoder.user.model;

import com.devdahcoder.user.contract.UserDetailsContract;
import com.devdahcoder.user.contract.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.stream.Collectors;

public class UserDetailsModel implements UserDetailsContract {

	private final UserModel userModel;

	public UserDetailsModel(UserModel userModel) {

		this.userModel = userModel;

	}

	@Override
	public UserRole getRole() {

		return userModel.getRole();

	}

	@Override
	public String getEmail() {

		return userModel.getEmail();

	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return userModel
				.getAuthorities()
				.stream()
				.map(authority -> new SimpleGrantedAuthority(authority.getAuthority()))
				.collect(Collectors.toList());

	}

	@Override
	public String getPassword() {

		return userModel.getPassword();

	}

	@Override
	public String getUsername() {

		return userModel.getUsername();

	}

	@Override
	public boolean isAccountNonExpired() {

		return userModel.isCredentialsNonExpired();

	}

	@Override
	public boolean isAccountNonLocked() {

		return userModel.isAccountNonLocked();

	}

	@Override
	public boolean isCredentialsNonExpired() {

		return userModel.isCredentialsNonExpired();

	}

	@Override
	public boolean isEnabled() {

		return userModel.isEnabled();

	}

}
