package com.example.marketplace.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.UUID;

@Getter
@Data
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sellers")
public class Seller {
    @Id
    @GeneratedValue
    @Column(name = "seller_id", unique = true)
    private UUID sellerId;
    @NotBlank(message="{password is invalid}")
    @Size(min = 4, max = 15, message="{size is invalid}")
    @Column(name = "seller_password")
    private String sellerPassword;
    @NotBlank(message="{First name is invalid}")
    @Size(min = 4, max = 30, message="{size is invalid}")
    @Column(name = "seller_first_name")
    private String sellerFirstName;
    @NotBlank(message="{Second name is invalid}")
    @Size(min = 4, max = 30, message="{size is invalid}")
    @Column(name = "seller_last_name")
    private String sellerLastName;
    @NotBlank(message="{Patro name is invalid}")
    @Column(name = "seller_patro_name")
    private String sellerPatroName;
    @NotBlank(message="{Number is invalid}")
    @Column(name = "seller_number")
    private String sellerNumber;
    @NotBlank(message="{UCard is invalid}")
    @Column(name = "seller_u_card", unique = true)
    private String sellerUCard;
    @NotBlank(message="{email is invalid}")
    @Column(name = "seller_email", unique = true)
    private String sellerEmail;
    @NotBlank(message="{phone is invalid}")
    @Column(name = "seller_phone", length = 11,  unique = true)
    private String sellerPhone;
    @NotBlank(message="{photo is invalid}")
    @Column(name = "seller_photo")
    private String sellerPhoto;
}
