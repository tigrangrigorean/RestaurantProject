package com.ordering_system.service;


import com.ordering_system.model.domain.ManagerEntity;
import com.ordering_system.model.dto.Manager;

import java.util.List;

public interface ManagerService {
    Manager getById(long id);
    List<Manager> getAll();
    ManagerEntity save(Manager manager);
    Manager update(Manager manager);
    void delete(long id);
}
