package com.system.order.manager.service;

import com.system.order.manager.model.User;
import com.system.order.manager.model.UserRequest;

import java.util.List;

public interface UserService {

    User updateUserById(Integer id, UserRequest userRequest);

    User getUserById(Integer id);

    void createUser(UserRequest userRequest);

    void deleteUserById(Integer id);

    List<User> listUsers();
}
