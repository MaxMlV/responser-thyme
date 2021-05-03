package com.maxmlv.responserthyme.services;

import com.maxmlv.responserthyme.models.User;
import com.maxmlv.responserthyme.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User findUserById(long user_id) {
        return userRepository.findById(user_id);
    }


    public String encodePassword(String password) {
        return bCryptPasswordEncoder.encode(password);
    }

    public User addUser(User user) {
        String encodedPass = encodePassword(user.getPassword());
        String encodedConfirmPass = encodePassword(user.getConfirmPassword());
        user.setPassword(encodedPass);
        user.setConfirmPassword(encodedConfirmPass);
        user.setActive(true);
        return userRepository.save(user);
    }

    public User updateUser(User user , String firstName, String lastName) {
        user.setFirstName(firstName);
        user.setLastName(lastName);
        return userRepository.save(user);
    }

    public void deleteUserById(long user_id) {
        User user = userRepository.findById(user_id);
        userRepository.delete(user);
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
