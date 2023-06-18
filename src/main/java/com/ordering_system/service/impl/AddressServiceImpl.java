package com.ordering_system.service.impl;


import com.ordering_system.model.domain.AddressEntity;
import com.ordering_system.model.dto.Address;
import com.ordering_system.repository.AddressRepository;
import com.ordering_system.service.AddressService;
import com.ordering_system.service.converter.Converter;
import com.ordering_system.service.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    private final Converter converter;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository, Converter converter) {
        this.addressRepository = addressRepository;
        this.converter = converter;
    }


    @Override
    public Address getById(long id) {
        Validator.checkId(id);
        AddressEntity addressEntity = addressRepository.findAddressEntityById(id);
        Validator.checkEntity(addressEntity);
        return converter.entityToAddress(addressEntity);
    }

    @Override
    public List<Address> getAll() {
        return converter.entityToAddressList(addressRepository.findAll());
    }

    @Override
    public Address save(Address address) {
        Validator.checkEntity(address);
        addressRepository.save(converter.addressToEntity(address));
        return address;
    }

    @Override
    public void update(long id, Address address) {
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
    }

    @Override
    public void delete(long id) {
        Validator.checkId(id);
        if (Validator.checkEntity(addressRepository.findAddressEntityById(id))) {
            addressRepository.deleteById(id);
        }
    }

}
