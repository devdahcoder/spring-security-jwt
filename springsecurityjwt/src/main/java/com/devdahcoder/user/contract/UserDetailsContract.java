package com.devdahcoder.user.contract;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailsContract extends UserDetails {

	String getEmail();

}
