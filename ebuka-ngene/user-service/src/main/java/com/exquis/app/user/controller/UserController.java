package com.exquis.app.user.controller;

import com.exquis.app.user.entity.User;
import com.exquis.app.user.service.contract.UserServiceContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("users")
public class UserController {
    @Autowired private UserServiceContract userService;

    @GetMapping("/")
    public List<User> users()
    {
        return userService.getAll();
    }

    @PutMapping("/")
    public User update(@RequestBody User user)
    {
        return userService.saveOrUpdate(user);
    }

    @GetMapping(value = "/{id}")
    public User getUserById(@PathVariable("id") String userId)
    {
        UUID uuid = UUID.fromString(userId);
        return userService.findById(uuid);
    }
}
