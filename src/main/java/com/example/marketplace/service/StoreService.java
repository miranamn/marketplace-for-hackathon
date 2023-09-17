package com.example.marketplace.service;

import com.example.marketplace.entity.Store;
import com.example.marketplace.exceptions.ValidUserException;
import com.example.marketplace.repository.StoreRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class StoreService {
    @Autowired
    StoreRepository storeRepository;
    public List<Store> getAllStores(){
        return storeRepository.findAll();
    }
    public Optional<Store> getStore(UUID id){
        return storeRepository.findById(id);
    }
    @Transactional
    public UUID createStore(Store store){
        return storeRepository.save(store).getStoreId();
    }
    @Transactional
    public ResponseEntity<String> deleteStore(UUID id){
        storeRepository.deleteById(id);
        return ResponseEntity.ok("DELETE was success");
    }
    @Transactional
    public ResponseEntity<String> updateStore(UUID id, Store store){
        Store storeTemp = this.storeRepository.findById(id).orElseThrow(() -> new ValidUserException(
                "store with id " + id + " does not exists"));
        storeTemp.setStoreName(store.getStoreName());
        storeTemp.setSeller(store.getSeller());
        storeTemp.setCategories(store.getCategories());
        storeTemp.setStoreAddress(store.getStoreAddress());
        storeTemp.setStoreDeaf(store.isStoreDeaf());
        storeTemp.setStoreDesc(store.getStoreDesc());
        storeTemp.setStoreField(store.getStoreField());
        storeTemp.setStorePhone(store.getStorePhone());
        storeTemp.setStoreEmail(store.getStoreEmail());
        storeTemp.setStoreBind(store.isStoreBind());
        storeTemp.setStorePhoto(store.getStorePhoto());
        return ResponseEntity.ok("update was success");
    }

}
