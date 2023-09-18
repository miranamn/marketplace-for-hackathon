package com.example.marketplace.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue
    @Column(name = "order_id", unique = true)
    private UUID orderId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany
    @Column(name = "position_id")
    private List<Position> positions;
    private Date data = Date.valueOf(LocalDate.now());
    @NotBlank(message="{description is invalid}")
    @Column(name = "order_desc")
    private String orderDesc;

}
