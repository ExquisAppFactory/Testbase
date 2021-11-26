package com.exquis.app.walletservice.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "activity_log")
public class ActivityLog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String walletNumber;
    private Double amount;
    @Column(nullable = false, unique = false)
    private UUID userId;

    @Column(nullable = false)
    private String remark;

    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @PrePersist
    private void onCreate()
    {
        createdAt = LocalDateTime.now();
    }
}
