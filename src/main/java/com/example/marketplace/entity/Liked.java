package com.example.marketplace.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Data
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "likes")
public class Liked {
    @Id
    @GeneratedValue
    @Column(name = "liked_id", unique = true)
    private UUID likedId;
    @OneToOne
    @PrimaryKeyJoinColumn(name = "position_id")
    private Position position_id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private Date data = Date.valueOf(LocalDate.now());
}
