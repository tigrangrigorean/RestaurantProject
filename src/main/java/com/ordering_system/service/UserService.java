package com.ordering_system.service;

import com.ordering_system.model.dto.User;

import java.util.List;

public interface UserService {
    User getById(long id);
    List<User> getAll();
    User save(User user);
    void update(long id, User user);
    void delete(long id);

}
