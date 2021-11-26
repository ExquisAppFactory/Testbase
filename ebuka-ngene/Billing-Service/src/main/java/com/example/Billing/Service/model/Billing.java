package com.example.Billing.Service.model;


import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "billing")
@Data
@Builder
public class Billing implements Serializable {

    private static final long serialVersionUID = -8800947452558953833L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "invoice_number")
    private double invoiceNumber;

    @Column(name = "billing_amount")
    private double billingAmount;

    @Column(name = "status")
    private boolean status;

    @Column(name = "user_id")
    private UUID userId;

    @JoinColumn(name = "item_id", referencedColumnName = "id")
    @ManyToOne
    private Item item;

    @Column(name = "date_created")
    private LocalDateTime dateCreated;


}
