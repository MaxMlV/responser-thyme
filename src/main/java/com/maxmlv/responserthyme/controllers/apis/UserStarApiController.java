package com.maxmlv.responserthyme.controllers.apis;

import com.maxmlv.responserthyme.models.User;
import com.maxmlv.responserthyme.services.UserService;
import com.maxmlv.responserthyme.services.UserStarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/star")
public class UserStarApiController {
    @Autowired
    private UserStarService userStarService;

    @Autowired
    private UserService userService;

    @PostMapping("/add/{user_id}")
    public String addStar(@AuthenticationPrincipal User sender,
                          @PathVariable("user_id") long owner_id) {
        userStarService.addStar(sender, userService.findUserById(owner_id));
        return "redirect:/profile/" + owner_id;
    }

    @PostMapping("/delete/{user_id}")
    public String deleteStar(@AuthenticationPrincipal User sender,
                             @PathVariable("user_id") long owner_id) {
        userStarService.deleteStar(sender, userService.findUserById(owner_id));
        return "redirect:/profile/" + owner_id;
    }
}
