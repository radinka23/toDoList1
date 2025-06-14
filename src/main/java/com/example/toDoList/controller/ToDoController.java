package com.example.toDoList.controller;

import com.example.toDoList.model.ItemEntity;
import com.example.toDoList.model.UserEntity;
import com.example.toDoList.repositorie.UserRepository;
import com.example.toDoList.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/todos")
public class ToDoController {

    @Autowired
    private ToDoService toDoService;

    @Autowired
    private UserRepository userRepo;

    @GetMapping
    public String viewToDos(Model model, Principal principal) {
        Optional<UserEntity> userOpt = userRepo.findByUsername(principal.getName());
        if (userOpt.isEmpty()) {
            return "redirect:/login"; 
        }
        UserEntity user = userOpt.get();
        List<ItemEntity> items = toDoService.getUserTasks(user);
        model.addAttribute("items", items);
        model.addAttribute("newItem", new ItemEntity());
        model.addAttribute("currentTime", LocalDateTime.now());
        
        return "todos";
    }


    @PostMapping("/add")
    public String addToDo(@ModelAttribute ItemEntity item, Principal principal) {
        Optional<UserEntity> userOpt = userRepo.findByUsername(principal.getName());
        if (userOpt.isEmpty()) {
            return "redirect:/login";
        }

        UserEntity user = userOpt.get();
        item.setUser(user);

        toDoService.addTask(item);
        return "redirect:/todos?action=added";
    }


    @PostMapping("/delete/{id}")
    public String deleteToDo(@PathVariable Long id) {
        toDoService.deleteTask(id);
        return "redirect:/todos?action=deleted";

    }

    @PostMapping("/toggle/{id}")
    public String toggleComplete(@PathVariable Long id) {
        toDoService.toggleComplete(id);
        return "redirect:/todos?action=completed";
    }

    @GetMapping("/edit/{id}")
    public String editTask(@PathVariable Long id, Model model) {
        ItemEntity item = toDoService.getById(id);
        model.addAttribute("item", item);
        return "edit";
    }

    @PostMapping("/update/{id}")
    public String updateTask(@PathVariable Long id, @ModelAttribute ItemEntity updatedItem) {
        toDoService.updateTask(id, updatedItem);
        return "redirect:/todos?action=edited";

    }
}