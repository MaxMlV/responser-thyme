package com.maxmlv.responserthyme.controllers.apis;


import com.maxmlv.responserthyme.models.Post;
import com.maxmlv.responserthyme.models.User;
import com.maxmlv.responserthyme.services.PostLikeService;
import com.maxmlv.responserthyme.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/like")
public class PostLikeApiController {
    @Autowired
    private PostLikeService postLikeService;

    @Autowired
    private PostService postService;

    @PostMapping("/add/{post_id}")
    public String like(@AuthenticationPrincipal User user, @PathVariable("post_id") long post_id, @RequestParam("redirect") String redirect) {
        postLikeService.addLike(postService.findPostById(post_id), user);
        return "redirect:/" + redirect;
    }

    @PostMapping("/delete/{post_id}")
    public String dislike(@AuthenticationPrincipal User user,
                          @PathVariable("post_id") long post_id,
                          @RequestParam("redirect") String redirect) {
        postLikeService.deleteLike(postService.findPostById(post_id), user);
        return "redirect:/" + redirect;
    }
}
