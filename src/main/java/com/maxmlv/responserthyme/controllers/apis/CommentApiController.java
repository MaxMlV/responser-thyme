package com.maxmlv.responserthyme.controllers.apis;

import com.maxmlv.responserthyme.models.User;
import com.maxmlv.responserthyme.services.CommentService;
import com.maxmlv.responserthyme.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/comment")
public class CommentApiController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private PostService postService;

    @PostMapping("/add/{post_id}")
    public String addComment(@AuthenticationPrincipal User user,
                             @PathVariable("post_id") long post_id,
                             @RequestParam("comment") String text) {
        commentService.addComment(text, user, postService.findPostById(post_id));
        return "redirect:/post/" + post_id;
    }

    @PostMapping("/delete/{post_id}/{comment_id}")
    public String deleteComment(@PathVariable("post_id") long post_id,
                                @PathVariable("comment_id") long comment_id) {
        commentService.deleteComment(comment_id);
        return "redirect:/post/" + post_id;
    }
}
