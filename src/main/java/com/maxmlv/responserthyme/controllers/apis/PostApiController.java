package com.maxmlv.responserthyme.controllers.apis;

import com.maxmlv.responserthyme.models.Post;
import com.maxmlv.responserthyme.models.User;
import com.maxmlv.responserthyme.services.CommentService;
import com.maxmlv.responserthyme.services.MediaFileService;
import com.maxmlv.responserthyme.services.PostService;
import com.maxmlv.responserthyme.services.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

@Controller
@RequestMapping("/api/post")
public class PostApiController {
    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private ReplyService replyService;

    @Autowired
    private MediaFileService mediaFileService;

    @PostMapping("/add")
    public String addPost(@AuthenticationPrincipal User user,
                          @RequestParam("text") String text,
                          @RequestParam("file") MultipartFile file) throws IOException {
        postService.addPost(text, file, user);
        return "redirect:/";
    }

    @PostMapping("/delete/{post_id}")
    public String delete(@PathVariable("post_id") long post_id,
                         @RequestParam("redirect") String redirect) throws IOException {
        Post post = postService.findPostById(post_id);

        if (post.getFilename() != null) {
        Path mediaFilePath = FileSystems.getDefault().getPath(uploadPath + "/" + post.getFilename());
        Files.delete(mediaFilePath);
        }

        mediaFileService.deleteByPost(post);
        replyService.deleteAllRepliesByPost(post);
        commentService.deleteAllCommentsByPost(post);
        postService.deletePost(post_id);

        return "redirect:/" + redirect;
    }
}
