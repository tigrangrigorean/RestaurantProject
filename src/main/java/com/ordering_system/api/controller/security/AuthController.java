package com.ordering_system.api.controller.security;


import com.ordering_system.service.mailsender.GetMail;
import com.ordering_system.service.validator.Validator;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.ordering_system.api.security.token.JwtTokenUtils;
import com.ordering_system.api.security.token.RequestDto;

import com.ordering_system.service.impl.UserServiceImpl;

@RestController
public class AuthController {
	private final GetMail getMail;
	
	private final UserServiceImpl userService;
	private final JwtTokenUtils jwtTokenUtils;
	private final AuthenticationManager authenticationManager;
	
	public AuthController(GetMail getMail, UserServiceImpl userService, JwtTokenUtils jwtTokenUtils, AuthenticationManager authenticationManager) {
		this.getMail = getMail;
		this.userService = userService;
		this.jwtTokenUtils = jwtTokenUtils;
		this.authenticationManager = authenticationManager;
	}
	
	@PostMapping("/auth")
	public String createAuthToken(@RequestBody RequestDto requestDto) {
		UserDetails userDetails = userService.loadUserByUsername(requestDto.getEmail());
		Validator.checkActivation(userService.getByEmail(requestDto.getEmail()).isActivated());
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDetails.getUsername(),requestDto.getPassword()));
		String token = jwtTokenUtils.generateToken(userDetails);
		getMail.setMail(jwtTokenUtils.getEmail(token));
		return token;
	}
	
}