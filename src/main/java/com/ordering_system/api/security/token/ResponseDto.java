package com.ordering_system.api.security.token;

public class ResponseDto {
	
	private String token;
	
	public ResponseDto() {
		
	}
	
	public ResponseDto(String token) {
		this.token = token;
	}

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}
	


}
