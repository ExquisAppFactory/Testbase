package com.exquis.app.walletservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDto implements dto {
    private UUID id;
    private String firstName;
    private String lastName;
    private String fullName;
    private String phone;
    private String email;
    private Set<RoleLiteDto> role;
    private String accessToken;
}
