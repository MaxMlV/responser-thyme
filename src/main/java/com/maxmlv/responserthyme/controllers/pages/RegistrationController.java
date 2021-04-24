package com.maxmlv.responserthyme.controllers.pages;

import com.maxmlv.responserthyme.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegistrationController {

    @GetMapping("/registration")
    public String getRegistrationPage(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }
}
