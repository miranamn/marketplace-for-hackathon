package com.example.marketplace.controller;

import com.example.marketplace.entity.User;
import com.example.marketplace.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping
    public List<User> getUsers(){
        return userService.getAllUsers();
    }
    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable("id") UUID id){
        return userService.getUserById(id);
    }
    @PostMapping("/registration")
    public UUID addUsers(@RequestBody @Valid User user){
        return userService.addUsers(user);
    }
    @PostMapping("/login")
    public Map<String, String> loginUser(@RequestBody @Valid Map<String, String> user){
        return Map.of("token", userService.loginUser(user));
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable("id") UUID id, @RequestBody @Valid User user){
        return userService.updateUser(id, user);
    }
}