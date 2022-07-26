package com.offer.requestOffer;

import java.util.ArrayList;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;



@SuppressWarnings("serial")
public class CustomUserDetails implements UserDetails {
	
	
	private Customer customer;
	
	public CustomUserDetails(Customer customer) {
		this.customer=customer;
	}
	
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<Role> roles = customer.getRoles();
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		for(Role role: roles) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		return authorities;
	}
	
	public String getPassword() {
		return customer.getPassword();
	}
	
	public String getUsername() {
		return customer.getUsername();
	}
	
	public boolean isAccountNonExpired() {
		return true;
	}
	public boolean isAccountNonLocked() {
		return true;
	}
	
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	public boolean isEnabled() {
		return customer.isEnabled();
	}
	
	public String getFullname() {
		return customer.getFirstname()+" "+customer.getLastname();
	}
	
	
	
	
	

}
