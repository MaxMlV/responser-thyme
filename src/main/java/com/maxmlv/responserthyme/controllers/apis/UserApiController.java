package com.maxmlv.responserthyme.controllers.apis;

import com.maxmlv.responserthyme.models.User;
import com.maxmlv.responserthyme.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/user")
public class UserApiController {
    @Autowired
    private UserService userService;

    @PostMapping
    public String addUser(User user, Model model) {
        if (userService.findUserByUsername(user.getUsername()) != null) {
            model.addAttribute("errMessage", "User already exist!");
            return "registration";
        } else {
            userService.addUser(user);
        }
        return "login";
    }


}
