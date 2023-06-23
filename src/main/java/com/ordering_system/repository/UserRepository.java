package com.ordering_system.repository;


import com.ordering_system.model.domain.UserEntity;

import com.ordering_system.model.enumeration.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    UserEntity findUserEntityById(long id);
    List<UserEntity> findUserEntitiesByRole(Role role);
    UserEntity findUserEntityByEmail(String email);
}
