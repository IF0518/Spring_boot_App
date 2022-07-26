
package com.offer.requestOffer;

import org.springframework.beans.factory.annotation.Autowired;



import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class CustomUserDetailsService  implements UserDetailsService{
	
	@Autowired
	CustomerRepository cRepo;
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		Customer cust = cRepo.findByUsername(username);
		
		
		if(cust == null ) {
			
			throw new UsernameNotFoundException("User not found");
		}
		
		return new CustomUserDetails(cust);
	}

}
