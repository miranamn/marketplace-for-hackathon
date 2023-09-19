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
    public UUID getUserByToken(String token){
        String mail = (String) Jwts.parser().parse(token).getHeader().get("email");
       // String mail = (String) Jwts.claims().get("email");
        return userRepository.findByUserEmail(mail).get().getUserId();
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
    public Map<String, Object> loginUser(Map<String, String> user){
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
        Map<String, Object> map = Map.of("token", token);
        Map<String, Object> newMap = new HashMap<>(map);
        newMap.put("userId", person.get().getUserId());
        newMap.put("userPassword", user.get("userPassword"));
        newMap.put("userFirstName", person.get().getUserFirstName());
        newMap.put("userLastName", person.get().getUserLastName());
        newMap.put("userBDay", person.get().getUserBDay());
        newMap.put("userUCard", person.get().getUserUCard());
        newMap.put("userEmail", person.get().getUserEmail());
        newMap.put("userPhone", person.get().getUserPhone());
        newMap.put("userAddress", person.get().getUserAddress());
        newMap.put("userPhoto", person.get().getUserPhoto());
        return newMap;
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
