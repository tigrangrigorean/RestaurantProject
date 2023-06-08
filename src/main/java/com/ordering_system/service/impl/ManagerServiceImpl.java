package com.ordering_system.service.impl;

import com.ordering_system.model.domain.ManagerEntity;
import com.ordering_system.model.domain.RestaurantEntity;
import com.ordering_system.model.dto.Manager;
import com.ordering_system.repository.ManagerRepository;
import com.ordering_system.service.ManagerService;
import com.ordering_system.service.converter.Converter;
import com.ordering_system.service.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerServiceImpl implements ManagerService {
    private final Converter converter;
    private final ManagerRepository managerRepository;

    @Autowired
    public ManagerServiceImpl(Converter converter, ManagerRepository managerRepository) {
        this.converter = converter;
        this.managerRepository = managerRepository;
    }


    @Override
    public Manager getById(long id) {
        Validator.checkId(id);
        Validator.checkEntity(managerRepository.findManagerEntityById(id));
        return converter.entityToManager(managerRepository.findManagerEntityById(id));
    }

    @Override
    public List<Manager> getAll() {
        return converter.entityToManagerList(managerRepository.findAll());
    }

    @Override
    public Manager save(Manager manager) {
        Validator.checkEntity(manager);
        Validator.checkName(manager.getFirstName());
        Validator.checkName(manager.getLastName());
        Validator.checkPassport(manager.getPassportNumber());
        Validator.checkPhoneNumber(manager.getPhoneNumber());
        managerRepository.save(converter.managerToEntity(manager));
        return manager;
    }

    @Override
    public void update(long id, Manager manager) {
        Validator.checkId(id);
        ManagerEntity managerEntity  = managerRepository.findManagerEntityById(id);
        Validator.checkEntity(manager);
        Validator.checkEntity(managerEntity);
        if (manager.getFirstName() != null && Validator.checkName(manager.getFirstName())) {
            managerEntity.setFirstName(manager.getFirstName());
        }
        if (manager.getLastName() != null && Validator.checkName(manager.getLastName())) {
            managerEntity.setLastName(manager.getLastName());
        }
        if (manager.getPassportNumber() != null && Validator.checkPassport(manager.getPassportNumber())) {
            managerEntity.setPassportNumber(manager.getPassportNumber());
        }
        if (manager.getPhoneNumber() != null && Validator.checkPhoneNumber(manager.getPhoneNumber())) {
            managerEntity.setPhoneNumber(manager.getPhoneNumber());
        }
        if (manager.getPassword() != null) {
            managerEntity.setPassword(manager.getPassword());
        }
        managerRepository.save(managerEntity);
    }

    @Override
    public void delete(long id) {
        Validator.checkId(id);
        if (Validator.checkEntity(managerRepository.findManagerEntityById(id))) {
            managerRepository.deleteById(id);
        }
    }

}
