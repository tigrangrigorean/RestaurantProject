package com.ordering_system.service.mailsender.model;

public class PasswordDto {
	
	private String lastPassword;
	private String newPassword;
	private String repeatNewPassword;
	
	public PasswordDto() {
		
	}

	/**
	 * @param lastPassword
	 * @param newPassword
	 * @param repeatNewPassword
	 */
	public PasswordDto(String lastPassword, String newPassword, String repeatNewPassword) {
		super();
		this.lastPassword = lastPassword;
		this.newPassword = newPassword;
		this.repeatNewPassword = repeatNewPassword;
	}

	/**
	 * @return the lastPassword
	 */
	public String getLastPassword() {
		return lastPassword;
	}

	/**
	 * @param lastPassword the lastPassword to set
	 */
	public void setLastPassword(String lastPassword) {
		this.lastPassword = lastPassword;
	}

	/**
	 * @return the newPassword
	 */
	public String getNewPassword() {
		return newPassword;
	}

	/**
	 * @param newPassword the newPassword to set
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	/**
	 * @return the repeatNewPassword
	 */
	public String getRepeatNewPassword() {
		return repeatNewPassword;
	}

	/**
	 * @param repeatNewPassword the repeatNewPassword to set
	 */
	public void setRepeatNewPassword(String repeatNewPassword) {
		this.repeatNewPassword = repeatNewPassword;
	}

}
