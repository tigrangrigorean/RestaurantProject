package com.ordering_system.repository;

import com.ordering_system.model.domain.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity,Long> {
    AddressEntity findAddressEntityById(long id);


}
