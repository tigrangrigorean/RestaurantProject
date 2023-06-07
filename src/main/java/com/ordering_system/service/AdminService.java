package com.ordering_system.service;


import com.ordering_system.model.dto.Admin;

import java.util.List;

public interface AdminService {

    Admin getById(long id);
    List<Admin> getAll();
    Admin save(Admin admin) ;
    Admin update(Admin admin);
    void delete(long id);
}
