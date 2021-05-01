package com.maxmlv.responserthyme.services;


import com.maxmlv.responserthyme.models.Comment;
import com.maxmlv.responserthyme.models.Post;
import com.maxmlv.responserthyme.models.User;
import com.maxmlv.responserthyme.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Comparator;
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

    public List<Comment> sortByCommentsByDate(List<Comment> comments) {
        Collections.sort(comments, new Comparator<Comment>() {
            @Override
            public int compare(Comment o1, Comment o2) {
                if (o1.getDate() == null || o2.getDate() == null)
                    return 0;
                return o1.getDate().compareTo(o2.getDate());
            }
        });
        return comments;
    }

    public void deleteComment(long comment_id) {
        commentRepository.delete(findCommentById(comment_id));
    }

    @Transactional
    public void deleteAllCommentsByPost(Post post) {
        commentRepository.deleteAllByPost(post);
    }
}
