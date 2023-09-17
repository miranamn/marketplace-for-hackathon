package com.example.marketplace.service;

import com.example.marketplace.entity.User;
import com.example.marketplace.exceptions.UsernameNotFoundException;
import com.example.marketplace.repository.UserRepository;
import com.example.marketplace.utilsSecurity.Crypt;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Date;

@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class UserService {
    @Autowired
    UserRepository userRepository;
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    @Transactional
    public ResponseEntity<String> addUsers(User user){
        user.setUserPassword(Crypt.hash(user.getUserPassword()));
        userRepository.save(user);
        return ResponseEntity.ok("registration was success");
    }
    @Transactional
    public String loginUser(Map<String, String> user){
        String mail = user.get("userEmail");
        Optional<User> person = userRepository.findByUserEmail(mail);
        String password = person.get().getUserPassword();
        String email = person.get().getUserEmail();

        if (!Crypt.verifyAndUpdateHash(user.get("userPassword"), password))
            throw new UsernameNotFoundException("Password invalid");

        String token = Jwts.builder()
                .setSubject("user")
                .claim("kid", "id")
                .claim("email", email)
                .claim("phone", person.get().getUserPhone())
                .claim("birthday", person.get().getUserBDay())
                .setExpiration(new Date(System.currentTimeMillis() + 300000))
                .compact();
        return token;
    }
}
