package com.example.toDoList.Repositories;

import com.example.toDoList.Model.ItemEntity;
import com.example.toDoList.Model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<ItemEntity, Long> {
    List<ItemEntity> findByUser(UserEntity user);
}
