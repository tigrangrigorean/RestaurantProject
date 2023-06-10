package com.ordering_system.service;



import com.ordering_system.model.dto.Address;

import java.util.List;

public interface AddressService {
    Address getById(long id);

    List<Address> getAll();

    Address save(Address address);

    void update(long id, Address address);
     void delete(long id);


}
