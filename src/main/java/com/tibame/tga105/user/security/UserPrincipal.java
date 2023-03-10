package com.tibame.tga105.user.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.tibame.tga105.user.entity.UserStatusVO;
import com.tibame.tga105.user.entity.UserVO;

@Service
public class UserPrincipal implements UserDetails{
	
	UserVO uservo;
	UserStatusVO statusvo;
	
	public UserPrincipal(UserVO uservo, UserStatusVO statusvo) {
		super();
		this.uservo = uservo;
		this.statusvo = statusvo;
	}
	

	public UserPrincipal() {
		super();
	}
	
	public UserPrincipal(UserVO uservo) {
		super();
		this.uservo = uservo;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return Collections.singleton(new SimpleGrantedAuthority(uservo.getUserstatusvo().getRolename()));
	}
	
	public UserVO getUservo() {
		return uservo;
	}

	public void setUservo(UserVO uservo) {
		this.uservo = uservo;
	}

	@Override
	public String getPassword() {
		
		return uservo.getUserpwd();
	}

	@Override
	public String getUsername() {
		
		return uservo.getUseremail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
