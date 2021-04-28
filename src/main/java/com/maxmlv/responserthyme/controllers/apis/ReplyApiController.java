package com.maxmlv.responserthyme.controllers.apis;


import com.maxmlv.responserthyme.models.Comment;
import com.maxmlv.responserthyme.models.Post;
import com.maxmlv.responserthyme.models.User;
import com.maxmlv.responserthyme.services.CommentService;
import com.maxmlv.responserthyme.services.PostService;
import com.maxmlv.responserthyme.services.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/reply")
public class ReplyApiController {
    @Autowired
    private ReplyService replyService;

    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    @PostMapping("/add/{post_id}/{comment_id}")
    public String addReply(@AuthenticationPrincipal User user,
                           @PathVariable("post_id") long post_id,
                           @PathVariable("comment_id") long comment_id,
                           @RequestParam("reply") String text) {
        replyService.addReply(text, postService.findPostById(post_id), user, commentService.findCommentById(comment_id));
        return "redirect:/post/" + post_id;
    }

    @PostMapping("/delete/{post_id}/{reply_id}")
    public String deleteReply(@PathVariable("post_id") long post_id,
                              @PathVariable("reply_id") long reply_id) {
        replyService.deleteReply(reply_id);
        return "redirect:/post/" + post_id;
    }
}
