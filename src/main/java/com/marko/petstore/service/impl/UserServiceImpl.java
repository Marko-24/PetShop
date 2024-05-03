package com.marko.petstore.service.impl;

import com.marko.petstore.model.User;
import com.marko.petstore.model.exceptions.InvalidUserIdException;
import com.marko.petstore.repository.PetRepository;
import com.marko.petstore.repository.UserRepository;
import com.marko.petstore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(InvalidUserIdException::new);
    }

    @Override
    public List<User> listAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> createUser(List<User> users) {
        return userRepository.saveAll(users);
    }
}
