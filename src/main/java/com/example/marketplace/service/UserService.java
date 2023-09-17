package com.example.marketplace.service;

import com.example.marketplace.entity.User;
import com.example.marketplace.exceptions.UsernameNotFoundException;
import com.example.marketplace.exceptions.ValidUserException;
import com.example.marketplace.repository.UserRepository;
import com.example.marketplace.utilsSecurity.Crypt;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class UserService {
    @Autowired
    UserRepository userRepository;
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    public Optional<User> getUserById(UUID id){
        return userRepository.findById(id);
    }
    @Transactional
    public UUID addUsers(User user){
        if (userRepository.findByUserPhone(user.getUserPhone()).isPresent() ||
                userRepository.findByUserEmail(user.getUserEmail()).isPresent())
            throw new ValidUserException("SUCH DATA IS TAKEN");
        else {
            user.setUserPassword(Crypt.hash(user.getUserPassword()));
            userRepository.save(user);
        }
        return userRepository.findByUserEmail(user.getUserEmail()).get().getUserId();
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
                .setExpiration(new Date(System.currentTimeMillis() + 300000))
                .compact();
        return token;
    }

    @Transactional
    public ResponseEntity<String> updateUser(UUID id, User user){
        User userTemp = this.userRepository.findById(id).orElseThrow(() -> new ValidUserException(
                "user with id " + id + " does not exists"));
        userTemp.setUserPassword(Crypt.hash(user.getUserPassword()));
        userTemp.setUserFirstName(user.getUserFirstName());
        userTemp.setUserLastName(user.getUserLastName());
        userTemp.setUserSex(user.isUserSex());
        userTemp.setUserUCard(user.getUserUCard());
        userTemp.setUserAddress(user.getUserAddress());
        userTemp.setUserPhoto(user.getUserPhoto());
        return ResponseEntity.ok("update was success");
    }
}
