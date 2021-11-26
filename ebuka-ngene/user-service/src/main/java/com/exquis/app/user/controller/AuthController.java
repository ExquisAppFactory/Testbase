package com.exquis.app.user.controller;

import com.exquis.app.user.dto.HttpResponseDto;
import com.exquis.app.user.dto.LoginRequestDto;
import com.exquis.app.user.dto.RegisterUserRequestDto;
import com.exquis.app.user.service.contract.AuthServiceContract;
import com.exquis.app.user.service.contract.UserServiceContract;
import com.exquis.app.user.utility.Helper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@CrossOrigin
@RestController
public class AuthController {
    @Autowired
    private AuthServiceContract authService;

    @ApiOperation("Users registration endpoint.")
    @PostMapping(
            value = "register",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterUserRequestDto requestDto)
    {
        HttpResponseDto httpResponseDto = authService.register(requestDto);

        return new ResponseEntity<>(httpResponseDto, httpResponseDto.getStatusCode()); // return bad request
    }

    @PostMapping(
            value = "login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("User Login endpoint.")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginRequestDto requestDto) throws Exception
    {
        HttpResponseDto successResponse = new HttpResponseDto();
        successResponse.setEntity("User Authentication"); // set entity string

        try{
            successResponse = authService.login(requestDto); // call login process
            if(Helper.isNotEmpty(successResponse.getData()))
            {

                successResponse.setStatusCode(HttpStatus.OK);
                successResponse.setMessage("Successful user login");
                successResponse.setData(successResponse.getData());

                return new ResponseEntity<>(successResponse, HttpStatus.OK);
            }
        }
        catch(Exception ex) // catch exception and return error message
        {
            successResponse.setStatusCode(HttpStatus.BAD_REQUEST);
            successResponse.setMessage("Something went wrong or Invalid credentials provided.");
        }

        return new ResponseEntity<>(successResponse, HttpStatus.BAD_REQUEST); // return bad request with object
    }
}
