package com.maxmlv.responserthyme.services;

import com.maxmlv.responserthyme.models.Post;
import com.maxmlv.responserthyme.models.PostLike;
import com.maxmlv.responserthyme.models.User;
import com.maxmlv.responserthyme.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

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

    public List<Post> findPostsByLikes(List<PostLike> likes) {
        List<Post> likedPosts = new ArrayList<>();
        for (PostLike like : likes){
            if (like != null)
                likedPosts.add(findPostById(like.getPost().getId()));
        }
        return likedPosts;
    }

    public List<Post> sortPostsByDate(List<Post> posts) {
        Collections.sort(posts, new Comparator<Post>() {
            @Override
            public int compare(Post o1, Post o2) {
                if (o1.getDate() == null || o2.getDate() == null)
                    return 0;
                return o2.getDate().compareTo(o1.getDate());
            }
        });
        return posts;
    }

    public List<Boolean> checkPostsByPrincipal(List<Post> posts, User user) {
        List<Boolean> checkedPostsByPrincipal = new ArrayList<>();

        for (Post post : posts) {
            boolean isLiked = false;
            for (PostLike like : post.getLikes()) {
                if (like.getUser().getId() == user.getId()) {
                    isLiked = true;
                    break;
                }
            }
            checkedPostsByPrincipal.add(isLiked);
        }
        return checkedPostsByPrincipal;
    }

    public Boolean checkPostByPrincipal(long post_id, User user) {
        Post post = findPostById(post_id);
        for (PostLike like : post.getLikes()) {
            if (like.getUser().getId() == user.getId())
                return true;
        }
        return false;
    }

    public Post addPost(String text, MultipartFile file, User user) throws IOException {
        Post newPost = new Post(text, user);

        Date date = new Date();
        newPost.setDate(date);

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
