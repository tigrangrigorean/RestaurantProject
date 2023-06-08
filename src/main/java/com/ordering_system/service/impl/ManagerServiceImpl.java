package com.ordering_system.service.impl;

import com.ordering_system.model.domain.ManagerEntity;
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
        managerRepository.save(converter.managerToEntity(manager));
        return manager;
    }

    @Override
    public Manager update(long id, Manager manager) {
        return null;
    }

    @Override
    public void delete(long id) {
        Validator.checkId(id);
        if (Validator.checkEntity(managerRepository.findManagerEntityById(id))) {
            managerRepository.deleteById(id);
        }
    }

}
