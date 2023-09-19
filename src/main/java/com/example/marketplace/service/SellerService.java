package com.example.marketplace.service;

import com.example.marketplace.entity.Seller;
import com.example.marketplace.entity.User;
import com.example.marketplace.exceptions.UsernameNotFoundException;
import com.example.marketplace.exceptions.ValidUserException;
import com.example.marketplace.repository.SellerRepository;
import com.example.marketplace.utilsSecurity.Crypt;
import io.jsonwebtoken.Jwts;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class SellerService {
    @Autowired
    SellerRepository sellerRepository;
    public List<Seller> getAllSellers(){
        return sellerRepository.findAll();
    }
    public Optional<Seller> getSellerById(UUID id){
        return sellerRepository.findById(id);
    }
    @Transactional
    public UUID addSellers(Seller seller){
        if (sellerRepository.findBySellerPhone(seller.getSellerPhone()).isPresent() ||
                sellerRepository.findBySellerEmail(seller.getSellerEmail()).isPresent() ||
                sellerRepository.findBySellerNumber(seller.getSellerNumber()).isPresent())
            throw new ValidUserException("SUCH DATA IS TAKEN");
        seller.setSellerPassword(Crypt.hash(seller.getSellerPassword()));
        sellerRepository.save(seller);
        return sellerRepository.findBySellerEmail(seller.getSellerEmail()).get().getSellerId();
    }

    @Transactional
    public Map<String, Object> loginSeller(Map<String, String> seller){
        String mail = seller.get("sellerEmail");
        Optional<Seller> person = sellerRepository.findBySellerEmail(mail);
        String password = person.get().getSellerPassword();
        String email = person.get().getSellerEmail();

        if (!Crypt.verifyAndUpdateHash(seller.get("sellerPassword"), password))
            throw new UsernameNotFoundException("Password invalid");

        String token = Jwts.builder()
                .setSubject("user")
                .claim("kid", "id")
                .claim("email", email)
                .claim("phone", person.get().getSellerPhone())
                .setExpiration(new Date(System.currentTimeMillis() + 300000))
                .compact();
        Map<String, Object> map = Map.of("token", token);
        Map<String, Object> newMap = new HashMap<>(map);
        newMap.put("sellerId", person.get().getSellerId());
        newMap.put("sellerPassword", seller.get("SellerPassword"));
        newMap.put("sellerFirstName", person.get().getSellerFirstName());
        newMap.put("sellerLastName", person.get().getSellerLastName());
        newMap.put("sellerPatroName", person.get().getSellerPatroName());
        newMap.put("sellerNumber", person.get().getSellerNumber());
        newMap.put("sellerUCard", person.get().getSellerUCard());
        newMap.put("sellerEmail", person.get().getSellerEmail());
        newMap.put("sellerPhone", person.get().getSellerPhone());
        newMap.put("sellerPhoto", person.get().getSellerPhoto());
        return newMap;
    }

    @Transactional
    public ResponseEntity<String> updateSeller(UUID id, Seller seller){
        Seller sellerTemp = this.sellerRepository.findById(id).orElseThrow(() -> new ValidUserException(
                "seller with id " + id + " does not exists"));
        sellerTemp.setSellerPassword(Crypt.hash(seller.getSellerPassword()));
        sellerTemp.setSellerFirstName(seller.getSellerFirstName());
        sellerTemp.setSellerLastName(seller.getSellerLastName());
        sellerTemp.setSellerPatroName(seller.getSellerPatroName());
        sellerTemp.setSellerUCard(seller.getSellerUCard());
        sellerTemp.setSellerPhoto(seller.getSellerPhoto());
        return ResponseEntity.ok("update was success");
    }

}
