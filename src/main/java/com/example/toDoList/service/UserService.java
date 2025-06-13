package com.example.toDoList.service;


import com.example.toDoList.model.UserEntity;
import com.example.toDoList.DTO.UserDTO;

import java.util.Optional;

public interface UserService {
    Optional<UserEntity> findByUsername(String username);

    UserEntity save(UserDTO userDTO);


}