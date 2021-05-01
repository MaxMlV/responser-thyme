package com.maxmlv.responserthyme.controllers.pages;

import com.maxmlv.responserthyme.models.MediaFile;
import com.maxmlv.responserthyme.models.Post;
import com.maxmlv.responserthyme.models.PostLike;
import com.maxmlv.responserthyme.models.User;
import com.maxmlv.responserthyme.services.PostLikeService;
import com.maxmlv.responserthyme.services.PostService;
import com.maxmlv.responserthyme.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private PostLikeService postLikeService;


    @GetMapping("/{username}")
    public String getUserProfilePosts(@AuthenticationPrincipal User principalUser,
                                      @PathVariable("username") String username, Model model) {
        User user = userService.findUserByUsername(username);
        List<Post> posts = postService.sortPostsByDate(user.getPosts());
        List<Boolean> isPrincipalLikedPostList = postService.checkPostsByPrincipal(posts, principalUser);
        model.addAttribute("principalUser", principalUser);
        model.addAttribute("user", user);
        model.addAttribute("posts", posts);
        model.addAttribute("isPrincipalLikedPostList", isPrincipalLikedPostList);
        return "profile";
    }

    @GetMapping("/{username}/media")
    public String getUserProfileMedia(@AuthenticationPrincipal User principalUser,
                                      @PathVariable String username, Model model) {
        User user = userService.findUserByUsername(username);
        List<MediaFile> mediaFiles = user.getMediaFiles();
        model.addAttribute("principalUser", principalUser);
        model.addAttribute("user", user);
        model.addAttribute("mediaFiles", mediaFiles);
        return "profile_media";
    }

    @GetMapping("/{username}/likes")
    public String getUserProfileLikes(@AuthenticationPrincipal User principalUser,
                                      @PathVariable("username") String username,Model model) {
        User user = userService.findUserByUsername(username);
        List<Post> posts = postService.sortPostsByDate(postService.findPostsByLikes(user.getLikes()));
        List<Boolean> isPrincipalLikedPostList = postService.checkPostsByPrincipal(posts, principalUser);
        model.addAttribute("principalUser", principalUser);
        model.addAttribute("user", user);
        model.addAttribute("posts", posts);
        model.addAttribute("isPrincipalLikedPostList", isPrincipalLikedPostList);
        return  "profile_likes";
    }
}
