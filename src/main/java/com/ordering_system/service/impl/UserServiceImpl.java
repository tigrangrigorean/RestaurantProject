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
import com.ordering_system.service.validator.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    private final Converter converter;

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;


    @Autowired
    public UserServiceImpl(Converter converter, UserRepository userRepository,
    		AddressRepository addressRepository) {
        this.converter = converter;
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
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
    public User getById(long id) {
        Validator.checkId(id);
        Validator.checkEntity(userRepository.findUserEntityById(id));
        return converter.entityToUser(userRepository.findUserEntityById(id));
    }

    @Override
    public List<User> getAll() {
        return converter.entityToUserList(userRepository.findAll());
    }

    @Override
    public User save(User user) {
    	if(userRepository.findUserEntityByEmail(user.getEmail()) != null) {
    		throw new EntityAlreadyExsistsException("Entity by Entered Email already exsists");
    	}
    	
        Validator.checkEntity(user);
        Validator.checkName(user.getFirstName());
        Validator.checkName(user.getLastName());
        Validator.checkId(user.getAddressId());
        Validator.checkEntity(addressRepository.findAddressEntityById(user.getAddressId()));
        Validator.checkPhoneNumber(user.getPhoneNumber());
        Validator.checkPassword(user.getPassword());
        user.setPassword(SecurityConfig.passwordEncoder().encode(user.getPassword()));
        Validator.checkEmail(user.getEmail());
        Validator.checkPassport(user.getPassportNumber());
        userRepository.save(converter.userToEntity(user));
        return user;
    }


//TODO
    @Override
    public void update(long id, User user) {

    }

    @Override
    public void delete(long id) {
        Validator.checkId(id);
        if (Validator.checkEntity(userRepository.findUserEntityById(id))) {
            userRepository.deleteById(id);
        }
    }
    


}
