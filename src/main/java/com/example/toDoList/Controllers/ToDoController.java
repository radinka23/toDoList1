package com.example.toDoList.Controllers;

import com.example.toDoList.Model.ItemEntity;
import com.example.toDoList.Model.UserEntity;
import com.example.toDoList.Repositories.UserRepository;
import com.example.toDoList.Services.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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
            return "redirect:/login"; // or show error
        }
        UserEntity user = userOpt.get();
        List<ItemEntity> items = toDoService.getUserTasks(user);
        model.addAttribute("items", items);
        model.addAttribute("newItem", new ItemEntity());
        return "todos";
    }


    @PostMapping("/add")
    public String addToDo(@ModelAttribute ItemEntity item, Principal principal) {
        Optional<UserEntity> userOpt = userRepo.findByUsername(principal.getName());
        if (userOpt.isEmpty()) {
            return "redirect:/login"; // or show an error page/message
        }

        UserEntity user = userOpt.get();
        item.setUser(user);
        toDoService.addTask(item);
        return "redirect:/todos";
    }


    @PostMapping("/delete/{id}")
    public String deleteToDo(@PathVariable Long id) {
        toDoService.deleteTask(id);
        return "redirect:/todos";
    }

    @PostMapping("/toggle/{id}")
    public String toggleComplete(@PathVariable Long id) {
        toDoService.toggleComplete(id);
        return "redirect:/todos";
    }
}
