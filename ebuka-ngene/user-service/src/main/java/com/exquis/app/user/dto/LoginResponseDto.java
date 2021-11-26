package com.exquis.app.user.dto;

import com.exquis.app.user.entity.Role;
import com.exquis.app.user.utility.Dto;
import lombok.Builder;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Builder
@Data
public class LoginResponseDto implements Dto {
    private UUID id;
    private String fullName;
    private String phone;
    private String email;
    private Set<Role> role; //RoleDto
    private String accessToken;
}
