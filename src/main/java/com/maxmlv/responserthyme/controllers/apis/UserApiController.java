package com.maxmlv.responserthyme.controllers.apis;

import com.maxmlv.responserthyme.models.User;
import com.maxmlv.responserthyme.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/api/user")
public class UserApiController {
    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public String addUser(User user, Model model) {
        if (userService.findUserByUsername(user.getUsername()) != null) {
            model.addAttribute("errMessage", "User already exist!");
            return "registration";
        } else if (!(user.getPassword().equals(user.getConfirmPassword()))) {
            model.addAttribute("errMessage", "Password not confirmed!");
            return "registration";
        } else {
            userService.addUser(user);
        }
        return "login";
    }


    @PostMapping("/update")
    public String updateUser(@AuthenticationPrincipal User user,
                                   @RequestParam("firstNameToUpdate") String firstName,
                                   @RequestParam("lastNameToUpdate") String lastName, Model model) {

        userService.updateUser(user, firstName, lastName);

        return "redirect:/profile/" + user.getUsername();
    }
}
