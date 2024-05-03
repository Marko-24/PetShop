package com.marko.petstore.service;

import com.marko.petstore.model.User;

import java.util.List;

public interface UserService {

    User findUserById(Long id);
    List<User> listAllUsers();
    List<User> createUser(List<User> users);
}
