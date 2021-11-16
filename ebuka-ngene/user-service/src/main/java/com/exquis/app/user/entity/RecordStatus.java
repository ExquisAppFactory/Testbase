package com.exquis.app.user.entity;

import com.exquis.app.user.enums.StatusType;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "record_status")
public class RecordStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "record_status_generator")
    @SequenceGenerator(name="record_status_generator", sequenceName = "record_status_seq", allocationSize=50, initialValue = 300)
    @Column(name = "id", updatable = false, nullable = false)
    private long id;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_by")
    private String deletedBy;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusType status;
}
