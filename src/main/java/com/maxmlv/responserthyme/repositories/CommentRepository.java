package com.maxmlv.responserthyme.repositories;


import com.maxmlv.responserthyme.models.Comment;
import com.maxmlv.responserthyme.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Comment findById(long comment_id);
    void deleteAllByPost(Post post);
}
