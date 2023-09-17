package com.example.marketplace.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.w3c.dom.Text;

import java.util.UUID;

@Getter
@Data
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "positions")
public class Position {
    @Id
    @GeneratedValue
    @Column(name = "position_id", unique = true)
    private UUID positionId;
    @NotBlank(message="{Name is invalid}")
    @Column(name = "position_name")
    private String positionName;
    @NotBlank(message="{description is invalid}")
    @Column(name = "position_desc")
    private Text positionDesc;
    @NotBlank(message="{Photo is invalid}")
    @Column(name = "position_photo")
    private String positionPhoto;
    @NotNull(message="{Price is invalid}")
    @Column(name = "position_price")
    private int positionPrice;
    @NotNull(message="{Count is invalid}")
    @Column(name = "position_count")
    private int positionCount;
}
