package com.maxmlv.responserthyme.repositories;

import com.maxmlv.responserthyme.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findById(long userId);
}
