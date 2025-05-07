package com.example.toDoList.Services;


import com.example.toDoList.Model.UserEntity;
import com.example.toDoList.UserDTO;

import java.util.Optional;

public interface UserService {
    Optional<UserEntity> findByUsername(String username);

    UserEntity save(UserDTO userDTO);

}