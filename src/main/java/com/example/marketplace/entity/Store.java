package com.example.marketplace.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Data
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "stores")
public class Store {
    @Id
    @GeneratedValue
    @Column(name = "store_id", unique = true)
    private UUID storeId;
    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;
    @OneToMany
    @Column(name = "category_id")
    private List<Category> categories;
    @NotBlank(message="{Name is invalid}")
    @Column(name = "store_name")
    private String storeName;
    @NotBlank(message="{description is invalid}")
    @Column(name = "store_desc")
    private String storeDesc;
    @NotNull(message="{field is invalid}")
    @Column(name = "store_field")
    private int storeField;
    @NotBlank
    @Column(name = "store_address")
    private String storeAddress;
    @NotBlank
    @Column(name = "store_phone")
    private String storePhone;
    @NotBlank
    @Column(name = "store_email")
    private String storeEmail;
    @NotNull
    @Column(name = "store_ramp")
    private boolean storeRamp;
    @NotNull
    @Column(name = "store_bind")
    private boolean storeBind;
    @NotNull
    @Column(name = "store_deaf")
    private boolean storeDeaf;
    @Column(name = "store_photo")
    private String storePhoto;

}
