package com.example.marketplace.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Data
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue
    @Column(name = "category_id", unique = true)
    private UUID categoryId;
    @OneToMany
    @Column(name = "position_id", unique = true)
    private List<Position> positions;
    @NotBlank(message="{Name is invalid}")
    @Column(name = "category_name")
    private String categoryName;
    @NotBlank(message="{Photo is invalid}")
    @Column(name = "category_photo")
    private String categoryPhoto;

}
