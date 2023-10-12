package com.artesanato.catalog.user.controller;

import com.artesanato.catalog.user.User;
import com.artesanato.catalog.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController (UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    // Return one single User by Id
    @GetMapping(path = "/get/user/{userId}")
    public Optional<User> getUserById(@PathVariable("userId") Long id ) {
        return userService.getUserById(id);
    }

    // Return one single User by Name
    @GetMapping(path = "/get/user/name/{userName}")
    public User getUserByName(@PathVariable("userName") String name ) {
        return userService.getUserByName(name);
    }

    // Create a new User in DB
    @PostMapping
    public void createUser(@RequestBody User user) {
        this.userService.addNewUser(user);
    }

    // Deletes a User in DB by ID
    @DeleteMapping(path = "deleteById/{userId}")
    public void deleteUser(@PathVariable("userId") Long id) {
        userService.deleteUser(id);
    }

    // Deletes List of Users in DB by ID
    @DeleteMapping(path = "deleteByIdList")
    public void deleteUserList(@RequestBody List<Long> idList) {
        for (Long id: idList) {
            userService.deleteUser(id);
        }
    }

    // Updates User email
    @PatchMapping(path = "/updateEmail/{userId}")
    public User patchUserEmail(@PathVariable long userId, @RequestBody Map<String, String> fields) {
        return userService.updateUserEmail(userId, fields);
    }

    // Updates User role
    @PatchMapping(path = "/updateRole/{userId}")
    public User patchUserRole(@PathVariable long userId, @RequestBody Map<String, String> fields) {
        return userService.updateUserRole(userId, fields);
    }

}
