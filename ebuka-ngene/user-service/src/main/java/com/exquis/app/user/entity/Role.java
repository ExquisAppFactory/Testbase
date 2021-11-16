package com.exquis.app.user.entity;

import com.exquis.app.user.enums.RoleType;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleType role;

    @Column(length = 250)
    private String description;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "record_status", referencedColumnName = "id", nullable = false, unique = true)
    private RecordStatus recordStatus;
}
