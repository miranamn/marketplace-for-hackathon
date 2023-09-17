package com.example.marketplace.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Data
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    @Column(name = "user_id", unique = true)
    private UUID userId;
    @NotBlank(message="{password is invalid}")
    @Column(name = "user_password")
    private String userPassword;
    @NotBlank(message="{First name is invalid}")
    @Size(min = 4, max = 30, message="{size is invalid}")
    @Column(name = "user_first_name")
    private String userFirstName;
    @NotBlank(message="{Second name is invalid}")
    @Size(min = 4, max = 30, message="{size is invalid}")
    @Column(name = "user_last_name")
    private String userLastName;
    @Column(name = "user_sex")
    private boolean userSex;
    @NotBlank(message="{birthday is invalid}")
    @Column(name = "user_bday")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate userBDay;
    @Column(name = "user_u_card", unique = true)
    private String userUCard;
    @NotBlank(message="{email is invalid}")
    @Column(name = "user_email", unique = true)
    private String userEmail;
    @NotBlank(message="{phone is invalid}")
    @Column(name = "user_phone", length = 11,  unique = true)
    private String userPhone;
    @Column(name = "user_address")
    private String userAddress;
    @Column(name = "user_photo")
    private String userPhoto;
}
