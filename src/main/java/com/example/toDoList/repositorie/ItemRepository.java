package com.example.toDoList.repositorie;

import com.example.toDoList.model.ItemEntity;
import com.example.toDoList.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<ItemEntity, Long> {
    List<ItemEntity> findByUser(UserEntity user);
}
