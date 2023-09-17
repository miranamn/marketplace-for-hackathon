package com.example.marketplace.controller;

import com.example.marketplace.entity.User;
import com.example.marketplace.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "api/user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping
    public List<User> getUsers(){
        return userService.getAllUsers();
    }
    @PostMapping("/registration")
    public ResponseEntity<String> addUsers(@RequestBody @Valid User user){
        return userService.addUsers(user);
    }
    @PostMapping("/login")
    public Map<String, String> loginUser(@RequestBody @Valid Map<String, String> user){
        return Map.of("token", userService.loginUser(user));
    }


}
