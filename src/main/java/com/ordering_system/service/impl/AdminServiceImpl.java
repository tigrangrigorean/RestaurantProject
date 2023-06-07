package com.ordering_system.service.impl;

import com.ordering_system.model.domain.AdminEntity;
import com.ordering_system.model.dto.Admin;
import com.ordering_system.repository.AdminRepository;
import com.ordering_system.repository.FoodRepository;
import com.ordering_system.service.AdminService;
import com.ordering_system.service.converter.Converter;
import com.ordering_system.service.validator.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AdminServiceImpl implements AdminService {
	
	private final AdminRepository adminRepository;
	private final Converter converter;
	
	@Autowired
	public AdminServiceImpl(AdminRepository adminRepository, Converter converter) {
		this.adminRepository = adminRepository;
		this.converter = converter;
	}
	
    @Override
    public Admin getById(long id) {
        Validator.checkId(id);
        Validator.checkEntity(adminRepository.findAdminEntityById(id));
        return converter.entityToAdmin(adminRepository.findAdminEntityById(id));
    }

    @Override
    public List<Admin> getAll() {
        return converter.entityToAdminList(adminRepository.findAll());
    }

    @Override
    public Admin save(Admin admin) {
    	Validator.checkEntity(admin);
    	adminRepository.save(converter.adminToEntity(admin));
    	return admin;
     }

    @Override
    public Admin update(long id,Admin admin) {
    	AdminEntity adminEntity = adminRepository.findAdminEntityById(id);
    	Validator.checkEntity(admin);
    	if(admin.getPhoneNumber() != null) {
    		adminEntity.setPhoneNumber(admin.getPhoneNumber());
    	}
    	if(admin.getPassword() != null) {
    		adminEntity.setPassword(admin.getPassword());
    	}
    	adminRepository.save(converter.adminToEntity(admin));
    	return admin;
    }

    @Override
    public void delete(long id) {
    	Validator.checkId(id);
    	Validator.checkEntity(adminRepository.findAdminEntityById(id));
    	adminRepository.deleteById(id);
    }
}
