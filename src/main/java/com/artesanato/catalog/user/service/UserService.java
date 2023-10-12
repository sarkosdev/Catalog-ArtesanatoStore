package com.artesanato.catalog.user.service;

import com.artesanato.catalog.user.User;
import com.artesanato.catalog.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Returns a List of Users in DB
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    // Returns one User by Id
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // Returns one User by Name
    public User getUserByName(String name) {
        return this.userRepository.findUserInDBByName(name);
    }

    // Add new User to DB
    public void addNewUser(User user) {
        System.out.println("POST REQUEST | TRYING TO CREATE User: " + user);

        Optional<User> userOptional = userRepository.findUserByName(user.getName());

        if(userOptional.isPresent()) {
            throw new IllegalStateException("user already exists");
        }
        else {
            userRepository.save(user);
        }
    }

    // Delete a user by ID
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // Patch User field Email
    public User updateUserEmail(long userId, Map<String, String> fields) {
        User user = new User();
        return user;

    }
}
