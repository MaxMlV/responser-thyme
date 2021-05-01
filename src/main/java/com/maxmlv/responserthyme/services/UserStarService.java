package com.maxmlv.responserthyme.services;

import com.maxmlv.responserthyme.models.User;
import com.maxmlv.responserthyme.models.UserStar;
import com.maxmlv.responserthyme.repositories.UserStarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserStarService {
    @Autowired
    private UserStarRepository userStarRepository;

    public UserStar findUserStarBySenderAndOwner(User sender, User owner) {
        return  userStarRepository.findBySenderAndOwner(sender, owner);
    }

    public UserStar addStar(User sender, User owner) {
        if (findUserStarBySenderAndOwner(sender, owner) == null) {
            UserStar userStar = new UserStar(sender, owner);
            return userStarRepository.save(userStar);
        }
        return null;
    }

    public void deleteStar(User sender, User owner) {
        userStarRepository.deleteBySenderAndOwner(sender, owner);
    }
}
