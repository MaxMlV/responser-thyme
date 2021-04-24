package com.maxmlv.responserthyme.controllers.apis;


import com.maxmlv.responserthyme.models.Post;
import com.maxmlv.responserthyme.models.User;
import com.maxmlv.responserthyme.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
public class CommentApiController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/add")
    public String addComment(@AuthenticationPrincipal User user,
                             @RequestParam Post post,
                             @RequestParam String text) {
        commentService.addComment(text, user, post);
        return "redirect:/post";
    }

    @DeleteMapping("/{comment_id}")
    public String deleteComment(@PathVariable("comment_id") long comment_id) {
        commentService.deleteComment(comment_id);
        return "";
    }
}
