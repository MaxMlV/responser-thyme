package com.maxmlv.responserthyme.services;


import com.maxmlv.responserthyme.models.Comment;
import com.maxmlv.responserthyme.models.Post;
import com.maxmlv.responserthyme.models.User;
import com.maxmlv.responserthyme.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public Comment findCommentById(long comment_id) {
        return commentRepository.findById(comment_id);
    }

    public Comment addComment(String text, User user, Post post) {
        Comment comment = new Comment(text, user, post);
        Date date = new Date();
        comment.setDate(date);
        return commentRepository.save(comment);
    }

    public void deleteComment(long comment_id) {
        commentRepository.delete(findCommentById(comment_id));
    }

    public void deleteAllCommentsByPost(Post post) {
        commentRepository.deleteAllByPost(post);
    }
}
