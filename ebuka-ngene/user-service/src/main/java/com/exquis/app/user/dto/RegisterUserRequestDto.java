package com.exquis.app.user.dto;

import com.exquis.app.user.utility.Dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserRequestDto implements Dto {
    @NotEmpty
    private String lastName;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String password;
    @NotEmpty
    private String email;
    @NotEmpty
    private String contactPhone;
}
