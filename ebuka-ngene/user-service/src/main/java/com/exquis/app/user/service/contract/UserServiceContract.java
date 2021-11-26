package com.exquis.app.user.service.contract;

import com.exquis.app.user.dto.RegisterUserRequestDto;
import com.exquis.app.user.dto.UserWalletResponseDto;
import com.exquis.app.user.entity.User;

import java.util.List;
import java.util.UUID;

public interface UserServiceContract {
    User saveOrUpdate(User user);
    List<User> getAll();
    User findById(UUID id);
    User findByEmail(String email);
    UserWalletResponseDto findUserWithWallet(UUID userId);
    User createUserAccount(RegisterUserRequestDto registerUserRequestDto);
}
