package com.maxmlv.responserthyme.controllers.pages;

import com.maxmlv.responserthyme.models.Post;
import com.maxmlv.responserthyme.models.User;
import com.maxmlv.responserthyme.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private PostService postService;

    @GetMapping("/")
    public String getAllPosts(@AuthenticationPrincipal User user, Model model) {
        List<Post> sortedPosts = postService.sortPostsByDate(postService.findAllPosts());
        List<Boolean> isPrincipalLikedPostList = postService.checkPostsByPrincipal(sortedPosts, user);
        model.addAttribute("principalUser", user);
        model.addAttribute("posts", sortedPosts);
        model.addAttribute("isPrincipalLikedPostList", isPrincipalLikedPostList);
        return "home";
    }
}
