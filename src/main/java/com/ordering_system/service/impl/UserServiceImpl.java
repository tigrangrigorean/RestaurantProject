package com.ordering_system.service.impl;

import com.ordering_system.model.dto.User;
import com.ordering_system.repository.AddressRepository;
import com.ordering_system.repository.UserRepository;
import com.ordering_system.service.UserService;
import com.ordering_system.service.converter.Converter;
import com.ordering_system.service.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Validator.checkEntity(user);
        Validator.checkName(user.getFirstName());
        Validator.checkName(user.getLastName());
        Validator.checkId(user.getAddressId());
        Validator.checkEntity(addressRepository.findAddressEntityById(user.getAddressId()));
        Validator.checkPhoneNumber(user.getPhoneNumber());
        Validator.checkPassword(user.getPassword());
        Validator.checkEmail(user.getEmail());
        userRepository.save(converter.userToEntity(user));
        return user;
    }


//TODO
    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public void delete(long id) {
        Validator.checkId(id);
        if (Validator.checkEntity(userRepository.findUserEntityById(id))) {
            userRepository.deleteById(id);
        }
    }
}
