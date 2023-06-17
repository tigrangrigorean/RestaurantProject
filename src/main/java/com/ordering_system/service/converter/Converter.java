package com.ordering_system.service.converter;

import com.ordering_system.model.domain.*;
import com.ordering_system.model.dto.*;
import com.ordering_system.repository.*;
import com.ordering_system.service.validator.Validator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Converter {

    private final FoodRepository foodRepository;
    private final AddressRepository addressRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public Converter(ModelMapper modelMapper,
                     FoodRepository foodRepository,
                     AddressRepository addressRepository,
                     UserRepository userRepository,
                     RestaurantRepository restaurantRepository,
                     OrderRepository orderRepository) {
        this.foodRepository = foodRepository;
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
        this.orderRepository = orderRepository;
    }

    /**
     * @param addressEntity
     * @return
     */
    public Address entityToAddress(AddressEntity addressEntity) {
        return new Address(addressEntity.getCity(),
                addressEntity.getStreet(),
                addressEntity.getBuilding(),
                addressEntity.getApartment());
    }

    public AddressEntity addressToEntity(Address address) {
        return new AddressEntity(address.getCity(),
                address.getStreet(),
                address.getBuilding(),
                address.getApartment());
    }

    public List<Address> entityToAddressList(List<AddressEntity> addressEntityList) {
        List<Address> addressList = new ArrayList<>();
        for (AddressEntity addressEntity : addressEntityList) {
            addressList.add(new Address(addressEntity.getCity(),
                    addressEntity.getStreet(),
                    addressEntity.getBuilding(),
                    addressEntity.getApartment()));
        }
        return addressList;
    }



    /**
     * @param foodEntity
     * @return
     */
    public Food entityToFood(FoodEntity foodEntity) {
        return new Food(foodEntity.getName(), foodEntity.getIngredient(), foodEntity.getPrice(),
                foodEntity.getRestaurantEntity().getId());
    }

    public FoodEntity foodToEntity(Food food) {
        return new FoodEntity(food.getName(),
                food.getIngredient(), food.getPrice(), restaurantRepository.findRestaurantEntityById(food.getRestaurantId()));
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
            foodList.add(new Food(foodEntity.getName(), foodEntity.getIngredient(), foodEntity.getPrice(),
                    foodEntity.getRestaurantEntity().getId()));
        }
        return foodList;
    }


    /**
     * @param restaurantEntity
     * @return
     */
    public Restaurant entityToRestaurant(RestaurantEntity restaurantEntity) {
        return new Restaurant(restaurantEntity.getName(),
                restaurantEntity.getTin(),
                restaurantEntity.getAddress().getId(),
                restaurantEntity.getUser().getId(),
                restaurantEntity.getFoundDate(),
                restaurantEntity.getRegistrationDate(),
                restaurantEntity.getPhoneNumber(),
                restaurantEntity.getEmail(),
                restaurantEntity.getBalance());
    }

    public RestaurantEntity restaurantToEntity(Restaurant restaurant) {
        RestaurantEntity restaurantEntity = new RestaurantEntity();
        restaurantEntity.setAddress(addressRepository.findAddressEntityById(restaurant.getAddressId()));
        restaurantEntity.setUser(userRepository.findUserEntityById(restaurant.getManagerId()));
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
        List<Restaurant> restaurantList = new ArrayList<>();
        for (RestaurantEntity restaurantEntity : restaurantEntityList) {
            restaurantList.add(
                    new Restaurant(restaurantEntity.getName(),
                            restaurantEntity.getTin(),
                            restaurantEntity.getAddress().getId(),
                            restaurantEntity.getUser().getId(),
                            restaurantEntity.getFoundDate(),
                            restaurantEntity.getRegistrationDate(),
                            restaurantEntity.getPhoneNumber(),
                            restaurantEntity.getEmail(),
                            restaurantEntity.getBalance())
            );
        }

        return restaurantList;
    }

    /**
     * @param userEntity
     * @return
     */
    public User entityToUser(UserEntity userEntity) {
        return new User(userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getBirthday(),
                userEntity.getPhoneNumber(),
                userEntity.getPassword(),
                userEntity.getEmail(),
                userEntity.getPassportNumber(),
                userEntity.getRole(),
                userEntity.getCardNumber());
    }

    public UserEntity userToEntity(User user) {
        return new UserEntity(user.getFirstName(),
                user.getLastName(),
                user.getBirthday(),
                user.getPhoneNumber(),
                user.getPassword(),
                user.getEmail(),
                user.getPassportNumber(),
                user.getRole(),
        		user.getCardNumber());
    }

    public List<User> entityToUserList(List<UserEntity> userEntityList) {
        List<User> userList = new ArrayList<>();
        for (UserEntity userEntity : userEntityList) {
            userList.add(new User(userEntity.getFirstName(),
                    userEntity.getLastName(),
                    userEntity.getBirthday(),
                    userEntity.getPhoneNumber(),
                    userEntity.getPassword(),
                    userEntity.getEmail(),
                    userEntity.getPassportNumber(),
                    userEntity.getRole(),
                    userEntity.getCardNumber()));
        }
        return userList;
    }

    /**
     * @param orderEntity
     * @return
     */
    public Order entityToOrder(OrderEntity orderEntity) {
        Order order = new Order();
        List<Long> foodIds = new ArrayList<>();
        for (FoodEntity foodEntity : orderEntity.getFoodList()) {
            foodIds.add(foodEntity.getId());
        }
        order.setFoodIdList(foodIds);
        order.setPrice(orderEntity.getTotalPrice());
        order.setRestaurantName(orderEntity.getRestaurantName());
        order.setOrderStatus(orderEntity.getOrderStatus());
        order.setDate(orderEntity.getDate());
        order.setDiscount(orderEntity.getDiscount());
        order.setDeliveryCost(orderEntity.getDeliveryCost());
        order.setDiscountedPrice(orderEntity.getDiscountedPrice());
        return order;
    }

    public OrderEntity orderToEntity(Order order) {
        OrderEntity orderEntity = new OrderEntity();
        List<FoodEntity> foodEntityList = new ArrayList<>();
        for (int i = 0; i < order.getFoodIdList().size(); i++) {
            foodEntityList.add(foodRepository.findFoodEntityById(order.getFoodIdList().get(i)));
        }
        orderEntity.setUserId(order.getUserId());
        orderEntity.setFoodList(foodEntityList);
        orderEntity.setTotalPrice(order.getPrice());
        orderEntity.setRestaurantName(order.getRestaurantName());
        orderEntity.setOrderStatus(order.getOrderStatus());
        orderEntity.setDate(order.getDate());
        orderEntity.setAddressToDelivery(order.getAddressToDelivery().toString());
        orderEntity.setDiscount(order.getDiscount());
        orderEntity.setDeliveryCost(order.getDeliveryCost());
        orderEntity.setDiscountedPrice(order.getDiscountedPrice());
        return orderEntity;
    }

    public List<Order> entityToOrderList(List<OrderEntity> orderEntityList) {
        List<Order> orderList = new ArrayList<>();
        for (OrderEntity orderEntity : orderEntityList) {
            List<Long> foodIds = new ArrayList<>();
            for (FoodEntity foodEntity : orderEntity.getFoodList()) {
                foodIds.add(foodEntity.getId());
            }
            orderList.add(new Order(orderEntity.getTotalPrice(),orderEntity.getDiscount(),orderEntity.getDiscountedPrice(),orderEntity.getDeliveryCost(),
                    foodIds,
                    orderEntity.getOrderStatus(),
                    orderEntity.getRestaurantName(),
                    orderEntity.getUserId(),
                    orderEntity.getDate()));
        }
        return orderList;
    }
    
    public Deliver entityToDeliver(DeliverEntity deliverEntity) {
    	Deliver deliver = new Deliver();
    	deliver.setUserId(deliverEntity.getUserEntity().getId());
    	deliver.setOrderId(deliverEntity.getOrderEntity().getId());
    	return deliver;
    }
    
    public DeliverEntity deliverToEntity(Deliver deliver) {
    	DeliverEntity deliverEntity = new DeliverEntity();
    	deliverEntity.setUserEntity(userRepository.findUserEntityById(deliver.getUserId()));
    	deliverEntity.setOrderEntity(orderRepository.findOrderEntityById(deliver.getOrderId()));
    	return deliverEntity;
    }
    


}
