package com.ordering_system.service.converter;

import com.ordering_system.model.domain.*;
import com.ordering_system.model.dto.*;
import com.ordering_system.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Converter {

    private  final ModelMapper modelMapper;
    private  final FoodRepository foodRepository;
    private  final AddressRepository addressRepository;
    private final ManagerRepository managerRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public Converter(ModelMapper modelMapper,
                     FoodRepository foodRepository,
                     AddressRepository addressRepository,
                     ManagerRepository managerRepository,
                     UserRepository userRepository,
                     RestaurantRepository restaurantRepository) {
        this.modelMapper = modelMapper;
        this.foodRepository = foodRepository;
        this.addressRepository = addressRepository;
        this.managerRepository = managerRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
    }

    /**
     *
     * @param addressEntity
     * @return
     */
    public Address entityToAddress(AddressEntity addressEntity){
        return new Address(addressEntity.getCity(),
                addressEntity.getStreet(),
                addressEntity.getBuilding(),
                addressEntity.getApartment());
    }

    public AddressEntity addressToEntity(Address address){
        return new AddressEntity(address.getCity(),
                address.getStreet(),
                address.getBuilding(),
                address.getApartment());
    }

    public List<Address> entityToAddressList(List<AddressEntity> addressEntityList) {
        List<Address> addressList =new ArrayList<>();
        for (AddressEntity addressEntity : addressEntityList) {
            addressList.add(new Address(addressEntity.getCity(),
                    addressEntity.getStreet(),
                    addressEntity.getBuilding(),
                    addressEntity.getApartment()));
        }
        return addressList;
    }

    /**
     *
     * @param adminEntity
     * @return
     */
    public Admin entityToAdmin(AdminEntity adminEntity){
        return new Admin(adminEntity.getPhoneNumber(),adminEntity.getPassword());
    }

    public AdminEntity adminToEntity(Admin admin){
        return new AdminEntity(admin.getPhoneNumber(),admin.getPassword());
    }

    public List<Admin> entityToAdminList(List<AdminEntity> adminEntityList){
        List<Admin> adminList = new ArrayList<>();
        for (AdminEntity adminEntity : adminEntityList) {
            adminList.add(new Admin(adminEntity.getPhoneNumber(),adminEntity.getPassword()));
        }
        return adminList;
    }
    /**
     *
     * @param foodEntity
     * @return
     */
    public Food entityToFood(FoodEntity foodEntity){
        return new Food(foodEntity.getName(),foodEntity.getIngredient(),foodEntity.getPrice(),
                foodEntity.getRestaurantEntity().getId());
    }

    public FoodEntity foodToEntity(Food food){
        return new FoodEntity(food.getName(),
                food.getIngredient(),food.getPrice(),restaurantRepository.findRestaurantEntityById(food.getRestaurantId()));
    }
    public List<FoodEntity> foodListToEntityList(List<Food> foodList) {
        List<FoodEntity> foodEntityList = new ArrayList<>();
        for (Food food : foodList) {
            foodEntityList.add(new FoodEntity(food.getName(),
                    food.getIngredient(), food.getPrice(), restaurantRepository.findRestaurantEntityById(food.getRestaurantId())));
        }
        return foodEntityList;
    }
     public List<Food> entityListToFoodList(List<FoodEntity> foodEntityList) {
       List<Food> foodList = new ArrayList<>();
        for (FoodEntity foodEntity : foodEntityList) {
            foodList.add(new Food(foodEntity.getName(),foodEntity.getIngredient(),foodEntity.getPrice(),
                    foodEntity.getRestaurantEntity().getId()));
        }
        return foodList;
    }

    /**
     *
     * @param managerEntity
     * @return
     */
    public Manager entityToManager(ManagerEntity managerEntity){

        return new Manager(managerEntity.getFirstName(),
                managerEntity.getLastName(),
                managerEntity.getPassportNumber(),
                managerEntity.getPhoneNumber(),
                managerEntity.getPassword());
    }

    public ManagerEntity managerToEntity(Manager manager){

        return new ManagerEntity(manager.getFirstName(),
                manager.getLastName(),
                manager.getPassportNumber(),
                manager.getPhoneNumber(),
                manager.getPassword());
    }

    public List<Manager> entityToManagerList(List<ManagerEntity> managerEntityList){
        List<Manager> managerList=new ArrayList<>();
        for (ManagerEntity managerEntity : managerEntityList) {
            managerList.add(new Manager(managerEntity.getFirstName(),
                    managerEntity.getLastName(),
                    managerEntity.getPassportNumber(),
                    managerEntity.getPhoneNumber(),
                    managerEntity.getPassword()));
        }
        return managerList;
    }
    /**
     *
     * @param restaurantEntity
     * @return
     */
    public Restaurant entityToRestaurant(RestaurantEntity restaurantEntity){
        return new Restaurant(restaurantEntity.getName(),
                restaurantEntity.getTin(),
                restaurantEntity.getAddress().getId(),
                restaurantEntity.getManager().getId(),
                restaurantEntity.getFoundDate(),
                restaurantEntity.getRegistrationDate(),
                restaurantEntity.getPhoneNumber(),
                restaurantEntity.getEmail());
    }

    public RestaurantEntity restaurantToEntity(Restaurant restaurant){
        RestaurantEntity restaurantEntity = new RestaurantEntity();
        restaurantEntity.setAddress(addressRepository.findAddressEntityById(restaurant.getAddressId()));
        restaurantEntity.setManager(managerRepository.findManagerEntityById(restaurant.getManagerId()));
        restaurantEntity.setName(restaurant.getName());
        restaurantEntity.setTin(restaurant.getTin());
        restaurantEntity.setFoundDate(restaurant.getFoundDate());
        restaurantEntity.setEmail(restaurant.getEmail());
        restaurantEntity.setPhoneNumber(restaurant.getPhoneNumber());
        restaurantEntity.setRegistrationDate(restaurant.getRegistrationDate());
        restaurantEntity.setFoodEntityList(foodRepository.findFoodEntitiesByRestaurantEntityId(restaurantEntity.getId()));
        return restaurantEntity;
    }
    
    public List<Restaurant> entityListToRestaurantList(List<RestaurantEntity> restaurantEntityList) {
    	List<Restaurant> restaurantList =new ArrayList<>();
        for (RestaurantEntity restaurantEntity : restaurantEntityList) {
            restaurantList.add(
                    new Restaurant(restaurantEntity.getName(),
                            restaurantEntity.getTin(),
                            restaurantEntity.getAddress().getId(),
                            restaurantEntity.getManager().getId(),
                            restaurantEntity.getFoundDate(),
                            restaurantEntity.getRegistrationDate(),
                            restaurantEntity.getPhoneNumber(),
                            restaurantEntity.getEmail())
            );
        }

    	return restaurantList;
    }

    /**
     *
     * @param userEntity
     * @return
     */
    public User entityToUser(UserEntity userEntity){
        return new User(userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getAddress().getId(),
                userEntity.getBirthday(),
                userEntity.getPhoneNumber(),
                userEntity.getPassword(),
                userEntity.getEmail());
    }

    public UserEntity userToEntity(User user){
        return new UserEntity(user.getFirstName(),
                user.getLastName(),
                addressRepository.findAddressEntityById(user.getAddressId()),
                user.getBirthday(),
                user.getPhoneNumber(),
                user.getPassword(),
                user.getEmail());
    }

    public List<User> entityToUserList(List<UserEntity> userEntityList){
        List<User> userList = new ArrayList<>();
        for (UserEntity userEntity : userEntityList) {
            userList.add( new User(userEntity.getFirstName(),
                    userEntity.getLastName(),
                    userEntity.getAddress().getId(),
                    userEntity.getBirthday(),
                    userEntity.getPhoneNumber(),
                    userEntity.getPassword(),
                    userEntity.getEmail()));
        }
        return userList;
    }

    /**
     *
     * @param orderEntity
     * @return
     */
    public Order entityToOrder(OrderEntity orderEntity){
        return modelMapper.map(orderEntity,Order.class);
    }

    public OrderEntity orderToEntity(Order order){
        return modelMapper.map(order, OrderEntity.class);
    }

    public List<Order> entityToOrderList(List<OrderEntity> orderEntityList){
        List<Order> orderList = orderEntityList
                .stream()
                .map(order -> modelMapper.map(order, Order.class))
                .collect(Collectors.toList());
        return orderList;
    }
}
