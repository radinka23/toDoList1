package com.example.toDoList.Controllers;

import java.util.Optional;

import com.example.toDoList.Model.UserEntity;
import com.example.toDoList.Services.UserService;

import com.example.toDoList.DTO.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class UserController {

    
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/login")
    public String login(Model model, UserDTO userDTO) {

        model.addAttribute("user", userDTO);
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model, UserDTO userDTO) {
        model.addAttribute("user", userDTO);
        return "register";
    }

//    @GetMapping("/welcome")
//    public String welcome(Model model, UserDTO userDTO, HttpSession session){
//        System.out.println(session.getId());
//        model.addAttribute("user", userDTO);
//        return "welcome";
//    }

    @PostMapping("/register")
    public String registerSava(@ModelAttribute("user") UserDTO userDTO, Model model) {
        Optional<UserEntity> user = userService.findByUsername(userDTO.getUsername());
        if (user.isPresent()) {
            model.addAttribute("Userexist", user);
            return "register";
        }
        userService.save(userDTO);
        return "redirect:/register?success";
    }
}