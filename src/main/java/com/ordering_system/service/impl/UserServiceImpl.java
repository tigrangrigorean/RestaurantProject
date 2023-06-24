package com.ordering_system.service.impl;

import com.ordering_system.api.security.config.SecurityConfig;
import com.ordering_system.model.domain.UserEntity;
import com.ordering_system.model.dto.User;
import com.ordering_system.model.enumeration.Role;
import com.ordering_system.model.exception.EntityAlreadyExistsException;
import com.ordering_system.model.exception.EntityNotFoundException;
import com.ordering_system.repository.AddressRepository;
import com.ordering_system.repository.UserRepository;
import com.ordering_system.service.RestaurantService;
import com.ordering_system.service.UserService;
import com.ordering_system.service.converter.Converter;
import com.ordering_system.service.mailsender.GetMail;
import com.ordering_system.service.mailsender.activation.MailActivation;
import com.ordering_system.service.mailsender.service.EmailSenderService;
import com.ordering_system.service.validator.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    private final Converter converter;

    private final UserRepository userRepository;
    private final EmailSenderService emailSenderService;
    private final AddressRepository addressRepository;
    private final MailActivation activation;
    private final GetMail getMail;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);


    @Autowired
    public UserServiceImpl(Converter converter, UserRepository userRepository,
                           EmailSenderService emailSenderService, AddressRepository addressRepository, MailActivation activation, GetMail getMail) {
        this.converter = converter;
        this.userRepository = userRepository;
        this.emailSenderService = emailSenderService;
        this.addressRepository = addressRepository;
        this.activation = activation;
        this.getMail = getMail;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        LOGGER.info("In method loadUserByUsername in UserServiceImpl class");
        UserEntity userEntity = userRepository.findUserEntityByEmail(email);
        if (userEntity == null) {
            throw new EntityNotFoundException("User by Entered Email not found");
        }
        String role = userEntity.getRole().name();
        UserDetails userDetails =  new org.springframework.security.core.userdetails.User(
                userEntity.getEmail(),
                userEntity.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority(role)));
        LOGGER.info("LoadUserByUsername method passed in UserServiceImpl class");
        return userDetails;
    }

    @Override
    public User getByEmail(String email) {
        LOGGER.info("In method getByEmail in UserServiceImpl class");
        User user = converter.entityToUser(userRepository.findUserEntityByEmail(email));
        LOGGER.info("GetByEmail method passed in UserServiceImpl class");
        return user;
    }

    @Override
    public User getById(long id ,Role role) {
        LOGGER.info("In method getById in UserServiceImpl class");
        Validator.checkId(id);
        UserEntity userEntity = userRepository.findUserEntityById(id);
        if(!userEntity.getRole().equals(role)){
            throw new EntityNotFoundException(role+" with id "+id+" not found");
        }
        Validator.checkEntity(userEntity);
        User user = converter.entityToUser(userEntity);
        LOGGER.info("GetById method passed in UserServiceImpl class");
        return user;
    }

    @Override
    public List<User> getAll(Role role) {
        LOGGER.info("In method getAll in UserServiceImpl class");
        List<User> userList =converter.entityToUserList(userRepository.findUserEntitiesByRole(role));
        LOGGER.info("GetAll method passed in UserServiceImpl class");
        return userList;
    }

    @Override
    public User save(User user) {
        LOGGER.info("In method save in UserServiceImpl class");
        if (userRepository.findUserEntityByEmail(user.getEmail()) != null) {
            throw new EntityAlreadyExistsException("Entity by Entered Email already exists");
        }

        Validator.checkEntity(user);
        Validator.checkName(user.getFirstName());
        Validator.checkName(user.getLastName());
        Validator.checkPhoneNumber(user.getPhoneNumber());
        Validator.checkPassword(user.getPassword());
        user.setPassword(SecurityConfig.passwordEncoder().encode(user.getPassword()));
        Validator.checkEmail(user.getEmail());
        Validator.checkPassport(user.getPassportNumber());
        Validator.checkCard(user.getCardNumber());
        userRepository.save(converter.userToEntity(user));
        activation.sendPin(user.getEmail());
        LOGGER.info("Save method passed in UserServiceImpl class");
        return user;
    }


    @Transactional
    @Override
    public void update(String email, User user) {
        LOGGER.info("In method update in UserServiceImpl class");
        UserEntity userEntity = userRepository.findUserEntityByEmail(email);
        Validator.checkEntity(user);
        Validator.checkEntity(userEntity);
        if (user.getEmail() != null  && Validator.checkEmail(user.getEmail())) {
            userEntity.setEmail(user.getEmail());
        }
        if (user.getFirstName() != null && Validator.checkName(user.getFirstName())) {
            userEntity.setFirstName(user.getFirstName());
        }
        if (user.getLastName() != null && Validator.checkName(user.getLastName())) {
            userEntity.setLastName(user.getLastName());
        }
        if (user.getPassword() != null && Validator.checkPassword(user.getPassword())) {
            userEntity.setPassword(SecurityConfig.passwordEncoder().encode(user.getPassword()));
        }
        if (user.getBirthday() != null) {
            userEntity.setBirthday(user.getBirthday());
        }
        if(user.getPassportNumber()!=null&&Validator.checkPassport(user.getPassportNumber())){
            userEntity.setPassword(user.getPassword());
        }
        if (user.getCardNumber() != null && Validator.checkCard(user.getCardNumber())) {
            userEntity.setCardNumber(user.getCardNumber());
        }
        if (user.getPhoneNumber() != null && Validator.checkPhoneNumber(user.getPhoneNumber())) {
            userEntity.setPhoneNumber(user.getPhoneNumber());
        }
        LOGGER.info("Update method passed in UserServiceImpl class");
    }

    @Override
    public void delete(long id,Role role) {
        LOGGER.info("In method delete in UserServiceImpl class");
        Validator.checkId(id);
        UserEntity userEntity = userRepository.findUserEntityByEmail(getMail.getMail());

        if(userEntity.getId() != id && role != Role.ADMIN) {
            throw new AccessDeniedException("Access denied");
        }

        if (Validator.checkEntity(userRepository.findUserEntityById(id))) {
            if(userRepository.findUserEntityById(id).getRole().equals(role)) {
                userRepository.deleteById(id);
            }
            else throw new EntityNotFoundException(role+" by id " +id+ " not found");
        }
        LOGGER.info("Delete method passed in UserServiceImpl class");
    }
    
    public void getAdminRoleUsingKey(User user,String key) {
    	if(!key.equals("adminkeyforregisteradministrator5555")) {
    		throw new AccessDeniedException("Entered Key is invalid");
    	}
    	user.setRole(Role.ADMIN);
    	save(user);
    }
}
