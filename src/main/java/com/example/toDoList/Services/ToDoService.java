package com.example.toDoList.Services;

import com.example.toDoList.Model.ItemEntity;
import com.example.toDoList.Model.UserEntity;
import com.example.toDoList.Repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
}
