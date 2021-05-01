package com.maxmlv.responserthyme.repositories;

import com.maxmlv.responserthyme.models.User;
import com.maxmlv.responserthyme.models.UserStar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserStarRepository extends JpaRepository<UserStar, Long> {
    UserStar findBySenderAndOwner(User sender, User owner);
    List<UserStar> findAllByOwner(User owner);
    void deleteBySenderAndOwner(User sender, User owner);
}
