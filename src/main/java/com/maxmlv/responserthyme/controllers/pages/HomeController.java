package com.maxmlv.responserthyme.controllers.pages;

import com.maxmlv.responserthyme.models.Post;
import com.maxmlv.responserthyme.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private PostService postService;

    @GetMapping
    public String getAllPosts(Model model) {
        List<Post> posts = postService.findAllPosts();
        model.addAttribute("posts", posts);
        return "home";
    }
}
