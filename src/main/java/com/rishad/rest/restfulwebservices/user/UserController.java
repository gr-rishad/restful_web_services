package com.rishad.rest.restfulwebservices.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserDaoService userDaoService;

    // retrieveAllUsers
    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return userDaoService.findAll();
    }

    //retrieveUser(int id)
    @GetMapping("/users/{id}")
    public User retrieveUserById(@PathVariable int id) {
        return userDaoService.findOne(id);
    }

    // save user
    // input--> user
    @PostMapping("/users")
    public void saveUser(@RequestBody User user) {
        userDaoService.save(user);
    }
}
