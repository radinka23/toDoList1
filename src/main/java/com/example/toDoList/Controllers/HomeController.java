package com.example.toDoList.Controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String homeRedirectIfAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Ако потребителят е логнат (не е анонимен)
        if (authentication != null && authentication.isAuthenticated()
                && !authentication.getPrincipal().equals("anonymousUser")) {
            return "redirect:/todos";
        }

        // Показваме началната страница (например welcome.html)
        return "welcome";
    }
}
