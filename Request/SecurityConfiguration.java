package com.offer.requestOffer;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity

public class SecurityConfiguration {
	@Autowired
	CustomerRepository cr;
	
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomUserDetailsService();
	}
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
         
        return authProvider;
    }
	
	
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
	
	@Bean
	public SecurityFilterChain filterchain(HttpSecurity http) throws Exception{
		 
		http
	    .authorizeHttpRequests().antMatchers("/postRequest","/saveRequest").hasAuthority("Request")
		.anyRequest().authenticated().and().formLogin().loginPage("/login")
		.loginProcessingUrl("/login").defaultSuccessUrl("/postRequest").permitAll()
		.and()
		.logout().logoutSuccessUrl("/").permitAll().
		logoutRequestMatcher(new AntPathRequestMatcher("/logout")).and().httpBasic();
		
		http.csrf().disable();
		http.headers().frameOptions().sameOrigin();
		
		return http.build();
	}
	
	
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		
		return (web) -> web.ignoring().antMatchers("/","/register","/Register","/register_success","/verify**","/verify_suc","/verify_fail","/css/**",
				"/images/**", "/js/**", "/webjars/**");
		

	}
	
	

}
