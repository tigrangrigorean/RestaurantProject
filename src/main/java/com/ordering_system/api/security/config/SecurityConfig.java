package com.ordering_system.api.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ordering_system.api.security.token.JwtRequestFilter;
import com.ordering_system.service.impl.UserServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private final UserServiceImpl userService;
	private final JwtRequestFilter jwtRequestFilter;
	
	@Autowired
	public SecurityConfig(UserServiceImpl userService,JwtRequestFilter jwtRequestFilter) {
		this.userService = userService;
		this.jwtRequestFilter = jwtRequestFilter;
	}
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.cors().disable()
				.csrf().disable()
				.authorizeHttpRequests((requests) -> requests
						.requestMatchers("/admin/getadminroleusingkey").permitAll()
						.requestMatchers("/admin/**").hasAuthority("ADMIN")
						.requestMatchers("/manager/register").permitAll()
						.requestMatchers("/manager/**").hasAnyAuthority("MANAGER","ADMIN")
						.requestMatchers("/order/register").authenticated()
						.requestMatchers("/order/get/**").authenticated()
						.requestMatchers("/order/**").hasAnyAuthority("MANAGER","ADMIN")
						.requestMatchers("/user").hasAuthority("ADMIN")
						.requestMatchers(HttpMethod.GET).permitAll()
						.requestMatchers("/food/**").hasAnyAuthority("MANAGER","ADMIN")
						.requestMatchers("/restaurant").permitAll()
						.requestMatchers("/restaurant/**").hasAnyAuthority("MANAGER","ADMIN")
						.requestMatchers("/delivery/singup").hasAnyAuthority("MANAGER","ADMIN")
						.requestMatchers("/delivery/update").hasAnyAuthority("MANAGER","ADMIN")
						.requestMatchers("/delivery/**").hasAnyAuthority("DELIVERY_MAN","MANAGER","ADMIN")
						.requestMatchers("/address/**").hasAnyAuthority("MANAGER","ADMIN")
						.requestMatchers("/user/register").permitAll()
						.requestMatchers("/user/**").authenticated()
						.requestMatchers("password/profile/changepassword").authenticated()
						.anyRequest().permitAll())
				.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
				.logout()
	                .logoutUrl("/logout")
	                .logoutSuccessUrl("/restaurant");
		return http.build();
	}
	
	
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		daoAuthenticationProvider.setUserDetailsService(userService);
		return daoAuthenticationProvider;
	}
	
	
	@Bean
	public static BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authentificationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
			return authenticationConfiguration.getAuthenticationManager();
	}
	
}
