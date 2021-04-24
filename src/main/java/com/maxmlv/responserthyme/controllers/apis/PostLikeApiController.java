package com.maxmlv.responserthyme.controllers.apis;


import com.maxmlv.responserthyme.models.Post;
import com.maxmlv.responserthyme.models.User;
import com.maxmlv.responserthyme.services.PostLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/like")
public class PostLikeApiController {
    @Autowired
    private
    PostLikeService postLikeService;

    @PostMapping
    public String like(@AuthenticationPrincipal User user,
                       @RequestParam("post") Post post) {
        postLikeService.addLike(post, user);
        return "";
    }

    @DeleteMapping
    public String dislike(@AuthenticationPrincipal User user,
                          @RequestParam("post") Post post) {
        postLikeService.deleteLike(post, user);
        return "";
    }
}
