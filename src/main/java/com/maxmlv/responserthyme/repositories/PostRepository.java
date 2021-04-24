package com.maxmlv.responserthyme.repositories;


import com.maxmlv.responserthyme.models.Post;
import com.maxmlv.responserthyme.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Post findById(long post_id);
    List<Post> findAllByUser(User user);
}
