package com.maxmlv.responserthyme.repositories;


import com.maxmlv.responserthyme.models.Post;
import com.maxmlv.responserthyme.models.PostLike;
import com.maxmlv.responserthyme.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    PostLike findById(long like_id);
    PostLike findByPostAndUser(Post post, User user);
    List<PostLike> findAllByUser(User user);
    void deleteAllByPost(Post post);
    void deleteByPostAndUser(Post post, User user);
}
