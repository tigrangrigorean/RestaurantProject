package com.ordering_system.service.validator;

import com.ordering_system.model.exception.*;

public class Validator {
	
	public static boolean checkId(long id) {
		if(id <= 0) {
			throw new IllegalArgumentException("Id is wrong");
		}
		return true;
	}
	
	public static boolean checkEntity(Object o) {
		if(o == null) {
			throw new EntityNotFoundException("Entity not found");
		}
		return true;
	}
	
	public static boolean checkPrice(double price) {
		if(price < 0) {
			throw new InvalidPriceException("Entered price is invalid");
		}
		return true;
	}

	public static boolean checkPhoneNumber(String phoneNumber){
		 String regex = "^\\+374\\d{8}$";

		if(!phoneNumber.matches(regex)){
			throw new InvalidPhoneNumberException("Entered phone number is invalid");
		}
		return true;
	}

	public static boolean checkPassword(String password){
		String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
		if(!password.matches(regex)){
			throw new InvalidPasswordException("Entered password is invalid, please make sure you have" +
					" provided at least one digit, one lowercase, one uppercase, one special character");
		}
		return true;
	}


    public static boolean checkTin(String tin){
        String regex = "^[0-9]{8}$";
        if(!tin.matches(regex)){
            throw new InvalidTinException("Entered TIN is invalid, please make sure you have" +
                    " entered correct TIN");
        }
        return true;
    }

	public static boolean checkEmail(String email){
		String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z]+\\.[A-Za-z]{2,}$";
		if(!email.matches(regex)){
			throw new InvalidEmailException("Entered email is invalid, please make sure you have" +
					" entered correct email");
		}
		return true;
	}




}
