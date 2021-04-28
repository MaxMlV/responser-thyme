package com.maxmlv.responserthyme.controllers.apis;


import com.maxmlv.responserthyme.models.User;
import com.maxmlv.responserthyme.services.CommentService;
import com.maxmlv.responserthyme.services.PostService;
import com.maxmlv.responserthyme.services.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/api/post")
public class PostApiController {
    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private ReplyService replyService;

    @PostMapping("/add")
    public String addPost(@AuthenticationPrincipal User user,
                          @RequestParam("text") String text,
                          @RequestParam("file") MultipartFile file) throws IOException {
        postService.addPost(text, file, user);
        return "redirect:/";
    }

    @PostMapping("/delete/{post_id}")
    public String delete(@PathVariable("post_id") long post_id) {
        replyService.deleteAllRepliesByPost(postService.findPostById(post_id));
        commentService.deleteAllCommentsByPost(postService.findPostById(post_id));
        postService.deletePost(post_id);
        return "redirect:/";
    }
}
