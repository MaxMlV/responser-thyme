package com.maxmlv.responserthyme.services;

import com.maxmlv.responserthyme.models.Post;
import com.maxmlv.responserthyme.models.User;
import com.maxmlv.responserthyme.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private MediaFileService mediaFileService;

    @Value("${upload.path}")
    private String uploadPath;

    public List<Post> findAllPosts() {
        return postRepository.findAll();
    }

    public Post findPostById(long post_id) {
        return postRepository.findById(post_id);
    }

    public Post addPost(String text, MultipartFile file, User user) throws IOException {
        Post newPost = new Post(text, user);

        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resFilename));

            newPost.setFilename(resFilename);
            newPost = postRepository.save(newPost);

            mediaFileService.addMediaFile(user, newPost, resFilename);

            return postRepository.save(newPost);
        }
        return postRepository.save(newPost);
    }

    public void deletePost(long post_id) {
        postRepository.delete(findPostById(post_id));
    }
}
