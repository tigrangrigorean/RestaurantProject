package com.ordering_system.service.validator;

import com.ordering_system.model.domain.*;
import com.ordering_system.model.dto.*;
import com.ordering_system.repository.RestaurantRepository;
import com.ordering_system.service.mailsender.GetMail;
import com.ordering_system.model.enumeration.Role;
import com.ordering_system.model.exception.*;
import com.ordering_system.repository.UserRepository;
import org.springframework.security.access.AccessDeniedException;

public class Validator {
    private final UserRepository userRepository;

    private  final GetMail getMail;

    public Validator(UserRepository userRepository, RestaurantRepository restaurantRepository, GetMail getMail) {
        this.userRepository = userRepository;
        this.getMail = getMail;
    }

    public static boolean checkId(long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Id is wrong");
        }
        return true;
    }

    public static boolean checkEntity(Address address){
        if(address == null){
            throw new EntityNotFoundException("Address not found");
        }
        return true;
    }

    public static boolean checkEntity(AddressEntity addressEntity){
        if(addressEntity == null){
            throw new EntityNotFoundException("Address entity not found");
        }
        return true;
    }
    public static boolean checkEntity(Delivery delivery){
        if(delivery == null){
            throw new EntityNotFoundException("Delivery not found");
        }
        return true;
    }
    public static boolean checkEntity(DeliveryEntity deliveryEntity){
        if(deliveryEntity == null){
            throw new EntityNotFoundException("Delivery entity not found");
        }
        return true;
    }
    public static boolean checkEntity(Food food){
        if(food == null){
            throw new EntityNotFoundException("Food not found");
        }
        return true;
    }
    public static boolean checkEntity(FoodEntity foodEntity){
        if(foodEntity == null){
            throw new EntityNotFoundException("Food entity not found");
        }
        return true;
    }
    public static boolean checkEntity(Order order){
        if(order == null){
            throw new EntityNotFoundException("Order not found");
        }
        return true;
    }

    public static boolean checkEntity(OrderEntity orderEntity){
        if(orderEntity == null){
            throw new EntityNotFoundException("Order entity not found");
        }
        return true;
    }
    public static boolean checkEntity(Restaurant restaurant){
        if(restaurant == null){
            throw new EntityNotFoundException("Restaurant not found");
        }
        return true;
    }
    public static boolean checkEntity(RestaurantEntity restaurantEntity){
        if(restaurantEntity == null){
            throw new EntityNotFoundException("Restaurant entity not found");
        }
        return true;
    }
    public static boolean checkEntity(User user){
        if(user == null){
            throw new EntityNotFoundException("User not found");
        }
        return true;
    }
    public static boolean  checkEntity(UserEntity userEntity){
        if(userEntity == null){
            throw new EntityNotFoundException("User entity not found");
        }
        return true;
    }



    public static boolean checkPrice(double price) {
        if (price < 0.1) {
            throw new InvalidPriceException("Entered price is invalid");
        }
        return true;
    }
    public  void checkAccess(RestaurantEntity restaurantEntity){
        UserEntity userEntity=userRepository.findUserEntityByEmail(getMail.getMail());
        if(userEntity.getId()!=restaurantEntity.getUser().getId()&&userEntity.getRole()!= Role.ADMIN){
            throw new AccessDeniedException("Access denied");
        }
    }

    public static boolean checkPhoneNumber(String phoneNumber) {
        String regex = "^\\+374\\d{8}$";

        if (!phoneNumber.matches(regex)) {
            throw new InvalidPhoneNumberException("Entered phone number is invalid");
        }
        return true;
    }

    public static boolean checkPassword(String password) {
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-/*+=])(?=\\S+$).{8,}$";
        if (!password.matches(regex)) {
            throw new InvalidPasswordException("Entered password is invalid, please make sure you have" +
                    " provided at least one digit, one lowercase, one uppercase, one special character");
        }
        return true;
    }


    public static boolean checkTin(String tin) {
        String regex = "^[0-9]{8}$";
        if (!tin.matches(regex)) {
            throw new InvalidTinException("Entered TIN is invalid, please make sure you have" +
                    " entered correct TIN");
        }
        return true;
    }

    public static boolean checkEmail(String email) {
        String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z]+\\.[A-Za-z]{2,}$";
        if (!email.matches(regex)) {
            throw new InvalidEmailException("Entered email is invalid, please make sure you have" +
                    " entered correct email");
        }
        return true;
    }

    public static boolean checkName(String name) {
        String regex = "[A-Za-z]+";
        if (!name.matches(regex)) {
            throw new IncorrectNameException("Name is incorrect. Enter only latin letters");
        }
        return true;
    }
    public static boolean checkPassport(String passport) {
        String regex = "^[A-Z]{2}\\d{7}$";
        System.out.println(passport.matches(regex));
        if (!passport.matches(regex)) {
            throw new InvalidPassportException("Enter valid passport number");
        }
        return true;
    }

    public static Address checkAddress(Address address) {
        String buildingPattern = "\\d{1,3}[A-Za-z]*/*\\d{0,2}[A-Za-z]*$";
        if (!address.getBuilding().matches(buildingPattern)) {
            throw new IncorrectAddressException("Incorrect building number");
        }
        address.setBuilding(address.getBuilding().toUpperCase());

        String streetPattern = "[A-Za-z ]*\\d{0,2}$";
        if (!address.getStreet().matches(streetPattern)) {
            throw new IncorrectAddressException("Incorrect street");
        }
        String street = address.getStreet();
        address.setStreet(street.substring(0, 1).toUpperCase() + street.substring(1));
        String apartmentPattern = "\\d[A-Za-z]*$";
        if (address.getApartment() != null && address.getApartment().matches(apartmentPattern)) {
            if (!address.getApartment().matches(apartmentPattern)) {
                throw new IncorrectAddressException("Incorrect apartment number");
            }
            address.setApartment(address.getApartment().toUpperCase());
        }
        return address;
    }
    public static boolean checkCard(String cardNumber) {
        String cardPattern = "\\d+";
        if (cardNumber.matches(cardPattern)) {
            if (cardNumber.length() == 16 && cardNumber.charAt(0) == '4') {
                return true;
            }
            String firstDigits = cardNumber.substring(0, 2);
            if (cardNumber.charAt(0) == '2' && cardNumber.length() == 16){
                return true;
            }
            if( cardNumber.charAt(0) == '5' && cardNumber.length() == 16){
                return true;
            }
            if (firstDigits.equals("37") &&cardNumber.length() == 15) {
                return true;
            }
            if ( firstDigits.equals("34") && cardNumber.length() == 15){
                return true;
            }

        }
        throw new NotValidCardException("Not valid card number");
    }
    
    public static boolean checkActivation(boolean activated) {
    	if(activated == false) {
    		throw new ActivationException("Account by entered e-mail isn't activated");
    	}
    	return true;
    }
}
