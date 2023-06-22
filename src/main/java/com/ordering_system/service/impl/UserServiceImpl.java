package com.ordering_system.service.impl;

import com.ordering_system.api.security.config.SecurityConfig;
import com.ordering_system.model.domain.UserEntity;
import com.ordering_system.model.dto.User;
import com.ordering_system.model.exception.EntityAlreadyExsistsException;
import com.ordering_system.model.exception.EntityNotFoundException;
import com.ordering_system.repository.AddressRepository;
import com.ordering_system.repository.UserRepository;
import com.ordering_system.service.UserService;
import com.ordering_system.service.converter.Converter;
import com.ordering_system.service.mailsender.activation.MailActivation;
import com.ordering_system.service.mailsender.service.EmailSenderService;
import com.ordering_system.service.validator.Validator;

import org.springframework.beans.factory.annotation.Autowired;
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


    @Autowired
    public UserServiceImpl(Converter converter, UserRepository userRepository,
                           EmailSenderService emailSenderService, AddressRepository addressRepository, MailActivation activation) {
        this.converter = converter;
        this.userRepository = userRepository;
        this.emailSenderService = emailSenderService;
        this.addressRepository = addressRepository;
        this.activation = activation;
    }


	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findUserEntityByEmail(email);
		if(userEntity == null) {
			throw new EntityNotFoundException("User by Entered Email not found");
		}
		  String role = userEntity.getRole().name();
		return new org.springframework.security.core.userdetails.User(
				userEntity.getEmail(),
				userEntity.getPassword(),
				Collections.singleton(new SimpleGrantedAuthority(role)));
	}

    @Override
    public User getByEmail(String email) {
        return converter.entityToUser(userRepository.findUserEntityByEmail(email));
    }

    @Override
    public User getById(long id) {
        Validator.checkId(id);
        UserEntity userEntity = userRepository.findUserEntityById(id);
        Validator.checkEntity(userEntity);
        return converter.entityToUser(userEntity);
    }

    @Override
    public List<User> getAll() {
        return converter.entityToUserList(userRepository.findAll());
    }

    @Override
    public User save(User user) {
    	if(userRepository.findUserEntityByEmail(user.getEmail()) != null) {
    		throw new EntityAlreadyExsistsException("Entity by Entered Email already exists");
    	}
    	
        Validator.checkEntity(user);
        Validator.checkName(user.getFirstName());
        Validator.checkName(user.getLastName());
        Validator.checkPhoneNumber(user.getPhoneNumber());
//        Validator.checkPassword(user.getPassword());
        user.setPassword(SecurityConfig.passwordEncoder().encode(user.getPassword()));
        Validator.checkEmail(user.getEmail());
//        Validator.checkPassport(user.getPassportNumber());
        Validator.checkCard(user.getCardNumber());
        userRepository.save(converter.userToEntity(user));
        activation.sendPin(user.getEmail());
        return user;
    }



    @Transactional
    @Override
    public void update(String email, User user){
        UserEntity userEntity= userRepository.findUserEntityByEmail(email);
        Validator.checkEntity(user);
        Validator.checkEntity(userEntity);
        if (user.getFirstName() != null) {
            userEntity.setFirstName(user.getFirstName());
        }
        if (user.getLastName() != null) {
            userEntity.setLastName(user.getLastName());
        }
        if(user.getPassword()!=null){
            userEntity.setPassword(SecurityConfig.passwordEncoder().encode(user.getPassword()));
        }
        if (user.getBirthday()!=null) {
            userEntity.setBirthday(user.getBirthday());
        }
        if (user.getPhoneNumber()!= null) {
            userEntity.setPhoneNumber(user.getPhoneNumber());
        }
        if (user.getRole() != null) {
            userEntity.setRole(user.getRole());
        }

        userRepository.save(userEntity);
    }

    @Override
    public void delete(long id) {
        Validator.checkId(id);
        if (Validator.checkEntity(userRepository.findUserEntityById(id))) {
            userRepository.deleteById(id);
        }
    }
}
