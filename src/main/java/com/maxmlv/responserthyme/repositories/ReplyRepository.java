package com.maxmlv.responserthyme.repositories;


import com.maxmlv.responserthyme.models.Post;
import com.maxmlv.responserthyme.models.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    Reply findById(long reply_id);
    void deleteAllByPost(Post post);
}
