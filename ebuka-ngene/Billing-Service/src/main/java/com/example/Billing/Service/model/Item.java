package com.example.Billing.Service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity(name = "item")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Item implements Serializable {
    private static final long serialVersionUID = 8553136258735824893L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "item_price")
    private double itemPrice;

    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    @Column(name = "quantity")
    private int quantity;

}
