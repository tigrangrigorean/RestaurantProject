# **OrderNow Project Documentation**
The OrderNow project is a system designed to facilitate online food ordering from various restaurants. It provides a user-friendly interface for users to browse restaurants, select their preferred restaurant, and place food orders. The project focuses on efficient order management, ensuring smooth communication between users, restaurants, and delivery personnel. It aims to streamline the process of ordering food online and provide a reliable infrastructure for a seamless user experience.
## **Table of Contents**
1. [Technologies Used](https://chat.openai.com/#technologies-used)
1. [Project Structure](https://chat.openai.com/#project-structure)
1. [API Package](https://chat.openai.com/#api-package)
1. [Service Package](https://chat.openai.com/#service-package)
1. [Repository Package](https://chat.openai.com/#repository-package)
1. [Model Package](https://chat.openai.com/#model-package)
1. [Enumerations](https://chat.openai.com/#enumerations)
## **Technologies Used <a name="technologies-used"></a>**
- Java 17
- Maven
- Spring Boot 3.1.0
- Spring Data JPA 3.1.0
- PostgreSQL
- Spring Security 6.1.0
- JSON Web Token (JWT)
- Swagger
- Spring Java Mail
- JUnit
- Mockito
- SLF4J (Logger)
- Scheduler 4.2.0
## **Project Structure <a name="project-structure"></a>**
The project follows a modular structure and consists of the following packages:

1. API: Contains the controller classes responsible for handling HTTP endpoints.
1. SERVICE: Contains interfaces and their implementations for business logic.
1. REPOSITORY: Contains repository interfaces that extend JpaRepository for database operations.
1. MODEL: Contains DTOs (Data Transfer Objects) and domain entities representing the data structure.
### **API Package <a name="api-package"></a>**
The API package contains the following controller classes with their respective endpoints:

- RestaurantController: Handles restaurant-related endpoints.
  - GET /restaurant: Retrieves information about all active restaurants.
  - GET /get/{id}: Retrieves information about a specific restaurant by ID.
  - POST /register: Saves a new restaurant to the database.
  - PUT /update: Updates some or all fields related to a restaurant.
  - DELETE /delete: Deletes a restaurant from the database.
- AddressController: Handles address-related endpoints.
  - GET /address: Retrieves information about all active addresses.
  - GET /get/{id}: Retrieves information about a specific address by ID.
  - POST /save: Saves a new address to the database.
  - PUT /update: Updates some or all fields related to an address.
  - DELETE /delete: Deletes an address from the database.
- FoodController: Handles food-related endpoints.
  - GET /food: Retrieves information about all active foods.
  - GET /get/{id}: Retrieves information about a specific food by ID.
  - GET /getAllByMenuId/{id}: Retrieves a list of foods belonging to a restaurant menu.
  - POST /save: Saves a new food to the database.
  - PUT /update: Updates some or all fields related to a food.
  - DELETE /delete: Deletes a food from the database.
- OrderController: Handles order-related endpoints.
  - GET /order: Retrieves information about all active orders.
  - GET /get/{id}: Retrieves information about a specific order by ID.
  - POST /register: Saves a new order to the database.
  - GET /get/orders/{id}: Retrieves all orders from an authenticated user.
  - DELETE /delete: Deletes an order by ID.
- UserController: Handles user-related endpoints.
  - GET /user: Retrieves information about all users.
  - GET /get/{id}: Retrieves information about a specific user by ID.
  - POST /register: Saves a new user to the database.
  - PUT /update: Updates some or all fields related to a user.
  - DELETE /delete: Deletes a user by ID.
- ManagerController: Handles manager-related endpoints.
  - GET /manager: Retrieves information about all managers.
  - GET /get/{id}: Retrieves information about a specific manager by ID.
  - POST /register: Saves a new manager to the database.
  - PUT /update: Updates some or all fields related to a manager.
  - DELETE /delete: Deletes a manager by ID.
- AdminController: Handles admin-related endpoints.
  - GET /admin: Retrieves information about all admins.
  - GET /get/{id}: Retrieves information about a specific admin by ID.
  - POST /register: Registers a new admin.
  - POST /getadminroleusingkey: Registers the first admin using a secret key.
  - GET /getnotactivatedrestaurants: Retrieves not activated restaurants.
  - GET /getAll: Retrieves information about all admins.
  - PUT /update: Updates some or all fields related to an admin.
  - DELETE /delete: Deletes an admin by ID.
- DeliveryController: Handles delivery-related endpoints.
  - GET /delivery: Retrieves information about all deliveries.
  - GET /get/{id}: Retrieves information about a specific delivery by ID.
  - POST /register: Saves a new delivery to the database.
  - PUT /update: Updates some or all fields related to a delivery.
  - PUT /updateToDelivered: Updates the order status to DELIVERED.
  - DELETE /delete: Deletes a delivery by ID.
- AuthController: Handles authentication-related endpoints.
  - POST /signin: Performs user login with username and password.
  - GET /profile/logout: Logs out the user.
- ChangePasswordController: Handles password change-related endpoints.
  - POST /password/profile/changepassword: Changes the password while being logged in.
  - POST /password/change: Changes the password with email.
  - GET /password/reset: Requests a password recovery with email.
- MailActivationController: Handles email activation-related endpoints.
  - POST /mail/send\_pin: Requests an activation pin with email.
  - POST /mail/activate: Activates a registered but not activated user.
### **SERVICE Package <a name="service-package"></a>**
The SERVICE package contains the following interfaces and their implementations:

- User: Provides methods for user-related business logic.
  - List<UserDTO> getAllUsers(): Retrieves information about all users.
  - UserDTO getUserById(Long id): Retrieves information about a specific user by ID.
  - UserDTO registerUser(UserDTO userDTO): Registers a new user.
  - UserDTO updateUser(Long id, UserDTO userDTO): Updates information about a user.
  - void deleteUser(Long id): Deletes a user by ID.
- Restaurant: Provides methods for restaurant-related business logic.
  - List<RestaurantDTO> getAllRestaurants(): Retrieves information about all active restaurants.
  - RestaurantDTO getRestaurantById(Long id): Retrieves information about a specific restaurant by ID.
  - RestaurantDTO registerRestaurant(RestaurantDTO restaurantDTO): Registers a new restaurant.
  - RestaurantDTO updateRestaurant(Long id, RestaurantDTO restaurantDTO): Updates information aboutthe restaurant.
  - void deleteRestaurant(Long id): Deletes a restaurant by ID.
- Address: Provides methods for address-related business logic.
  - List<AddressDTO> getAllAddresses(): Retrieves information about all active addresses.
  - AddressDTO getAddressById(Long id): Retrieves information about a specific address by ID.
  - AddressDTO saveAddress(AddressDTO addressDTO): Saves a new address.
  - AddressDTO updateAddress(Long id, AddressDTO addressDTO): Updates information about an address.
  - void deleteAddress(Long id): Deletes an address by ID.
- Food: Provides methods for food-related business logic.
  - List<FoodDTO> getAllFoods(): Retrieves information about all active foods.
  - FoodDTO getFoodById(Long id): Retrieves information about a specific food by ID.
  - List<FoodDTO> getFoodsByMenuId(Long id): Retrieves a list of foods belonging to a restaurant menu.
  - FoodDTO saveFood(FoodDTO foodDTO): Saves a new food.
  - FoodDTO updateFood(Long id, FoodDTO foodDTO): Updates information about a food.
  - void deleteFood(Long id): Deletes a food by ID.
- Order: Provides methods for order-related business logic.
  - List<OrderDTO> getAllOrders(): Retrieves information about all active orders.
  - OrderDTO getOrderById(Long id): Retrieves information about a specific order by ID.
  - OrderDTO registerOrder(OrderDTO orderDTO): Registers a new order.
  - List<OrderDTO> getOrdersByUserId(Long userId): Retrieves all orders from an authenticated user.
  - void deleteOrder(Long id): Deletes an order by ID.

The service interfaces include methods for retrieving, creating, updating, and deleting entities. These methods provide the necessary business logic for the application.
### **REPOSITORY Package <a name="repository-package"></a>**
The REPOSITORY package contains repository interfaces that extend the JpaRepository interface for performing database operations. These interfaces provide methods for CRUD (Create, Read, Update, Delete) operations and additional queries.

The repository interfaces include:

- UserRepository: Performs database operations for the User entity.
- RestaurantRepository: Performs database operations for the Restaurant entity.
- AddressRepository: Performs database operations for the Address entity.
- OrderRepository: Performs database operations for the Order entity.
- MenuRepository: Performs database operations for the Menu entity.

These interfaces inherit common methods from JpaRepository, such as findById(), findAll(), save(), deleteById(), etc., which allow for easy database interaction.
### **MODEL Package <a name="model-package"></a>**
The MODEL package contains DTOs (Data Transfer Objects) and domain entities representing the data structure of the application.

1. Data Transfer Objects (DTOs): These objects are used for transferring data between the API layer and the service layer. They have similar attributes to the corresponding domain entities but are tailored for specific use cases. The DTOs include:
   1. RestaurantDTO: Contains information about a restaurant, such as name, TIN, address ID, manager ID, found date, registration date, phone number, and email.
   1. UserDTO: Represents a user and includes attributes such as first name, last name, address ID, birthday, phone number, password, email, passport number, and role.
   1. AddressDTO: Represents an address and includes attributes such as city, street, building, and apartment.
   1. FoodDTO: Represents a food item and includes attributes such as name, ingredients, price, and restaurant ID.
   1. OrderDTO: Represents an order and includes attributes such as price, a list of food IDs, order status, and restaurant name.
1. Domain Entities: These entities represent the database tables and are used for persistence. They include additional attributes and associations compared to the DTOs. The domain entities include:
   1. RestaurantEntity: Represents a restaurant and includes attributes such as ID, name, TIN, associated address entity, associated user entity, found date, registration date, phone number, email, and a list of associated food entities.
   1. UserEntity: Represents a user and includes attributes such as ID, first name, last name, associated address entity, birthday, phone number, password, email, passport number, role, and a list of associated restaurant entities.
   1. AddressEntity: Represents an address and includes attributes such as ID, city, street, building, and apartment.
   1. FoodEntity: Represents a food item and includes attributes such as ID, name, ingredients, price, and associated restaurant entity.
   1. OrderEntity: Represents an order and includes attributes such as ID, price, a list of associated food entities, order status, and restaurant name.

The DTOs are used for transferring data between the API and service layers, while the domain entities represent the database structure and are used for persistence and data retrieval.
### **Enumerations <a name="enumerations"></a>**
The project utilizes enumerations to represent certain types of data. The enumerations include:

- Role: Represents different user roles such as ROLE\_USER, ROLE\_ADMIN, ROLE\_MANAGER, and ROLE\_DELIVERY\_MAN.
  - ROLE\_USER: Represents a user role with basic permissions to make orders.
  - ROLE\_ADMIN: Represents an admin role with elevated permissions to control all restaurants, accept restaurant applications, accept manager applications, and has all permissions of managers and users.
  - ROLE\_MANAGER: Represents a manager role with permissions to manage a specific restaurant and has all permissions of users.
  - ROLE\_DELIVERY\_MAN: Represents a delivery personnel role with limited permissions to deliver food.
- OrderStatus: Represents different order statuses such as ACCEPTED, PENDING, IN\_DELIVERY, DELIVERED, and REJECTED.
  - ACCEPTED: Indicates that the order has been accepted.
  - PENDING: Indicates that the order has been accepted, and the food is being prepared.
  - IN\_DELIVERY: Indicates that the food has left the restaurant and is being delivered.
  - DELIVERED: Indicates that the user has received the order.
  - REJECTED: Indicates that the payment for the order did not pass correctly.

These enumerations provide a structured way to represent and handle specific types of data in the application.
