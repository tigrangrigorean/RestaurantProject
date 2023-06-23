package com.ordering_system.service.impl;


import com.ordering_system.model.domain.AddressEntity;
import com.ordering_system.model.dto.Address;
import com.ordering_system.repository.AddressRepository;
import com.ordering_system.service.AddressService;
import com.ordering_system.service.converter.Converter;
import com.ordering_system.service.validator.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    private final Converter converter;
    private static final Logger LOGGER = LoggerFactory.getLogger(AddressService.class);

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository, Converter converter) {
        this.addressRepository = addressRepository;
        this.converter = converter;
    }


    @Override
    public Address getById(long id) {
        LOGGER.info("In method getById in AddressServiceImpl class");
        Validator.checkId(id);
        AddressEntity addressEntity = addressRepository.findAddressEntityById(id);
        Validator.checkEntity(addressEntity);
        Address address = converter.entityToAddress(addressEntity);
        LOGGER.info("GetById method passed in AddressServiceImpl class");
        return address;
    }

    @Override
    public List<Address> getAll() {
        LOGGER.info("In method getAll in AddressServiceImpl class");
        List<Address> addressList = converter.entityToAddressList(addressRepository.findAll());
        LOGGER.info("GetAll method passed in AddressServiceImpl class");
        return addressList;
    }

    @Override
    public Address save(Address address) {
        LOGGER.info("In method save in AddressServiceImpl class");
        Validator.checkEntity(address);
        addressRepository.save(converter.addressToEntity(address));
        LOGGER.info("Save method passed in AddressServiceImpl class");
        return address;
    }

    @Override
    public void update(long id, Address address) {
        LOGGER.info("In method update in AddressServiceImpl class");
        Validator.checkId(id);
        AddressEntity addressEntity = addressRepository.findAddressEntityById(id);
        Validator.checkEntity(address);
        Validator.checkEntity(addressEntity);

        if (address.getCity() != null) {
            addressEntity.setCity(address.getCity());
        }
        if (address.getStreet() != null) {
            addressEntity.setStreet(address.getStreet());
        }
        if (address.getBuilding() != null) {
            addressEntity.setBuilding(address.getBuilding());
        }

        if (address.getApartment() != null) {
            addressEntity.setApartment(address.getApartment());
        }

        addressRepository.save(addressEntity);
        LOGGER.info("Update method passed in AddressServiceImpl class");
    }

    @Override
    public void delete(long id) {
        LOGGER.info("In method delete in AddressServiceImpl class");
        Validator.checkId(id);
        if (Validator.checkEntity(addressRepository.findAddressEntityById(id))) {
            addressRepository.deleteById(id);
        }
        LOGGER.info("Delete method passed in AddressServiceImpl class");
    }

}
