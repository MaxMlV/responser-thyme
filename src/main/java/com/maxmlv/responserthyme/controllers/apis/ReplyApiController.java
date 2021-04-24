package com.maxmlv.responserthyme.controllers.apis;


import com.maxmlv.responserthyme.models.Comment;
import com.maxmlv.responserthyme.models.Post;
import com.maxmlv.responserthyme.models.User;
import com.maxmlv.responserthyme.services.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reply")
public class ReplyApiController {
    @Autowired
    private ReplyService replyService;

    @PostMapping("/add")
    public String addReply(@AuthenticationPrincipal User user,
                           @RequestParam("post") Post post,
                           @RequestParam("comment") Comment comment,
                           @RequestParam("reply") String text) {
        replyService.addReply(text, post, user, comment);
        return "";
    }

    @DeleteMapping
    public String deleteReply(@RequestParam("reply_id") long reply_id) {
        replyService.deleteReply(reply_id);
        return "";
    }
}
