package com.exquis.app.user.service.contract;

import com.exquis.app.user.dto.HttpResponseDto;
import com.exquis.app.user.dto.LoginRequestDto;
import com.exquis.app.user.dto.RegisterUserRequestDto;

public interface AuthServiceContract {
    HttpResponseDto register(RegisterUserRequestDto registerUserRequestDto);
    HttpResponseDto login(LoginRequestDto loginRequestDto);
}
