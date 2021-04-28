package com.maxmlv.responserthyme.controllers.pages;

import com.maxmlv.responserthyme.models.User;
import com.maxmlv.responserthyme.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping("/{post_id}")
    public String getPost(@AuthenticationPrincipal User user, @PathVariable("post_id") long post_id, Model model) {
        model.addAttribute("post", postService.findPostById(post_id));
        model.addAttribute("principalUser", user);
        model.addAttribute("isPostLikedByPrincipal", postService.checkPostByPrincipal(post_id, user));
        return "post";
    }
}
