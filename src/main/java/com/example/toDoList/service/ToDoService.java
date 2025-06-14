package com.example.toDoList.service;

import com.example.toDoList.model.ItemEntity;
import com.example.toDoList.model.UserEntity;
import com.example.toDoList.repositorie.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToDoService {
    @Autowired
    private ItemRepository toDoRepo;

    public List<ItemEntity> getUserTasks(UserEntity user) {
        return toDoRepo.findByUser(user);
    }

    public void addTask(ItemEntity item) {
        toDoRepo.save(item);
    }

    public void deleteTask(Long id) {
        toDoRepo.deleteById(id);
    }

    public void toggleComplete(Long id) {
        ItemEntity item = toDoRepo.findById(id).orElseThrow();
        item.setCompleted(!item.isCompleted());
        toDoRepo.save(item);
    }

    public ItemEntity getById(Long id) {
        return toDoRepo.findById(id).orElseThrow();
    }

    public void updateTask(Long id, ItemEntity updatedItem) {
        ItemEntity original = toDoRepo.findById(id).orElseThrow();
        original.setTitle(updatedItem.getTitle());
        original.setDescription(updatedItem.getDescription());
        original.setDueDate(updatedItem.getDueDate());
        toDoRepo.save(original);
    }

}
