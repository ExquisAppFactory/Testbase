package com.exquis.app.user.dto;

import com.exquis.app.user.utility.Dto;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class LoginRequestDto implements Dto {
    @Email(message = "Email is required")
    private String email;
    @NotEmpty(message = "Password is required")
    private String password;
}
