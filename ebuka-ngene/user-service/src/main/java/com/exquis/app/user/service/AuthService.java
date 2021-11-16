package com.exquis.app.user.service;

import com.exquis.app.user.constant.Generic;
import com.exquis.app.user.dto.HttpResponseDto;
import com.exquis.app.user.dto.LoginRequestDto;
import com.exquis.app.user.dto.LoginResponseDto;
import com.exquis.app.user.dto.RegisterUserRequestDto;
import com.exquis.app.user.entity.User;
import com.exquis.app.user.enums.StatusType;
import com.exquis.app.user.exception.HttpBadRequestException;
import com.exquis.app.user.exception.HttpForbiddenException;
import com.exquis.app.user.service.contract.AuthServiceContract;
import com.exquis.app.user.service.contract.RoleServiceContract;
import com.exquis.app.user.utility.Helper;
import com.exquis.app.user.utility.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService implements AuthServiceContract {
    @Autowired
    private UserService userService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired private JwtTokenUtil jwtTokenUtil;
    @Autowired private RoleServiceContract roleService;

    static String ENTITY = "authentication";

    @Override
    public HttpResponseDto register(RegisterUserRequestDto registrationRequestDto) {
        HttpResponseDto httpResponseDto = new HttpResponseDto();
        httpResponseDto.setEntity("User Registration");

        User user = userService.createUserAccount(registrationRequestDto);
        if(Helper.isNotEmpty(user)) {
            httpResponseDto.setStatusCode(HttpStatus.CREATED);
            httpResponseDto.setMessage("Successful user registration");
            httpResponseDto.setData(new ResponseEntity(user, HttpStatus.CREATED));
        }
        else{
            httpResponseDto.setStatusCode(HttpStatus.BAD_REQUEST);
            httpResponseDto.setMessage("Not successful");
        }

        return httpResponseDto;
    }

    @Override
    public HttpResponseDto login(LoginRequestDto loginRequestDto) {
        final String accessToken;
        HttpResponseDto successResponse = new HttpResponseDto();
        //LoginResponseDto loginResponseDto = new LoginResponseDto();
        Authentication authentication = null;

        // I am checking all entries here in case model validation does not work
        if(Helper.isEmpty(loginRequestDto.getEmail()) || Helper.isEmpty(loginRequestDto.getPassword())) {
            throw new HttpBadRequestException("Please fill in all fields.");
        }
        // validate that is an email
        if(!loginRequestDto.getEmail().matches(Generic.EMAIL_REG_EX))
        {
            throw new HttpBadRequestException("Invalid Credentials.");
        }

        // confirm that password is valid
        List<String> validPassword = Helper.isValidPassword(loginRequestDto.getPassword());

        if(Helper.isNotEmpty(validPassword) && !validPassword.isEmpty())
        {
            throw new HttpBadRequestException(validPassword.toString());
        }

        try{
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(), loginRequestDto.getPassword()));
        }
        catch(BadCredentialsException ex){
            throw new HttpBadRequestException("Invalid Credentials");
        }

        if(Helper.isNotEmpty(authentication))
        {
            SecurityContextHolder.getContext().setAuthentication(authentication);
            accessToken = jwtTokenUtil.generateToken(authentication);
            // we need to get user object to set our login-response-object
            User user = userService.findByEmail(loginRequestDto.getEmail().trim()); // get user by phone or email

            if(Helper.isNotEmpty(user)) // check user object
            {
                // make sure role if this user is active
                if(!roleService.checkRoleStatus(user.getRoles(), StatusType.ACTIVE))
                {
                    throw new HttpForbiddenException("User role is not active and can't login.");
                }

                LoginResponseDto loginResponseDto = LoginResponseDto.builder()
                        .id(user.getId())
                        .email(user.getEmail())
                        .accessToken(accessToken)
                        .fullName(user.getLastName() + user.getFirstName())
                        .phone(user.getContactPhone())
                        .role(user.getRoles())
                        .build();

                successResponse.setData(new ResponseEntity(loginResponseDto, HttpStatus.OK)); // set to data response with ok http status
            }
        }

        return successResponse;
    }
}
