package com.system.order.manager.service.impl;

import com.system.order.manager.model.User;
import com.system.order.manager.model.UserEntity;
import com.system.order.manager.model.UserRequest;
import com.system.order.manager.repository.UserRepository;
import com.system.order.manager.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User updateUserById(Integer id, UserRequest userRequest) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(NoClassDefFoundError::new);

        userEntity.setEmail(userRequest.getEmail());
        userEntity.setName(userRequest.getName());

        userRepository.save(userEntity);

        logger.info("USER UPDATED: User ID " + userEntity.getUserId());

        User user = new User();

        user.setName(userEntity.getName());
        user.setEmail(userEntity.getEmail());

        return user;
    }

    @Override
    public User getUserById(Integer id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(NoClassDefFoundError::new);

        User user = new User();

        user.setEmail(userEntity.getEmail());
        user.setName(userEntity.getName());

        return user;
    }

    @Override
    public void createUser(UserRequest userRequest) {
        UserEntity userEntity = new UserEntity();

        userEntity.setName(userRequest.getName());
        userEntity.setEmail(userRequest.getEmail());

        userRepository.save(userEntity);

        logger.info("USER CREATED: User ID " + userEntity.getUserId());

    }

    @Override
    public void deleteUserById(Integer id) {
        userRepository.deleteById(id);
        logger.warn("USER DELETED: User ID " + id);
    }

    @Override
    public List<User> listUsers() {
        List<UserEntity> userEntityList = userRepository.findAll();
        List<User> userList =  new ArrayList<>();

        for(UserEntity userEntity : userEntityList) {
            User user = new User();

            user.setEmail(userEntity.getEmail());
            user.setName(userEntity.getName());

            userList.add(user);
        }

        return userList;
    }
}
