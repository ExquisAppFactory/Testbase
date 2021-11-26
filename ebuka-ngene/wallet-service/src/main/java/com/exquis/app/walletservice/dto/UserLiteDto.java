package com.exquis.app.walletservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLiteDto implements dto {
    private String id;
    private String lastName;
    private String firstName;
    private String fullName;
    private String email;
    private String phoneNumber;
    private Set<RoleLiteDto> roles;
}
