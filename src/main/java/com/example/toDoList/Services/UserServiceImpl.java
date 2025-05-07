package com.example.toDoList.Services;


import com.example.toDoList.Model.UserEntity;
import com.example.toDoList.Repositories.UserRepository;
import com.example.toDoList.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    PasswordEncoder passwordEncoder;

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    @Override
    public Optional<UserEntity> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserEntity save(UserDTO userDTO) {
        UserEntity user = new UserEntity(userDTO.getUsername(), passwordEncoder.encode(userDTO.getPassword()));
        return userRepository.save(user);
    }

}