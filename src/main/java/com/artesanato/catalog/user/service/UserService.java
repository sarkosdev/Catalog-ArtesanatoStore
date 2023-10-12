package com.artesanato.catalog.user.service;

import com.artesanato.catalog.product.Product;
import com.artesanato.catalog.product.service.ProductService;
import com.artesanato.catalog.user.User;
import com.artesanato.catalog.user.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

    private static final Logger logger = LogManager.getLogger(ProductService.class);
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
        logger.info("TRYING TO PATCH USER -> email");
        User user = new User();
        Optional<User> existingUser = userRepository.findById(userId);

        if(existingUser.isPresent()) {
            if(fields.containsKey("email")) {
                String email = fields.get("email");
                User u = userRepository.findUserById(userId);

                user.setId(u.getId());
                user.setName(u.getName());
                user.setEmail(email);
                user.setUserRole(u.getUserRole());
                user.setCarts(u.getCarts());

                userRepository.save(user);
                logger.info("User after: ", user);
            }
        }
        else {
            throw new IllegalStateException("PATCH email -> User doesnt exist so it cant be updated");
        }

        return user;
    }

    // Patch User field Role
    public User updateUserRole(long userId, Map<String, String> fields){
        logger.info("TRYING TO PATCH USER -> role");
        User user = new User();
        Optional<User> existingUser = userRepository.findById(userId);

        if(existingUser.isPresent()) {
            if(fields.containsKey("role")) {
                String role = fields.get("role");
                User u = userRepository.findUserById(userId);

                user.setId(u.getId());
                user.setName(u.getName());
                user.setEmail(u.getEmail());
                user.setUserRole(role);
                user.setCarts(u.getCarts());

                userRepository.save(user);
                logger.info("User after: ", user);
            }
        }
        else {
            throw new IllegalStateException("PATCH role -> User doesnt exist so it cant be updated");
        }
        return user;
    }




}
