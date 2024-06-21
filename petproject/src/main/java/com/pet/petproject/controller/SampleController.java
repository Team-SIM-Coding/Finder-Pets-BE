package com.pet.petproject.controller;

import com.pet.petproject.data.User;
import com.pet.petproject.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SampleController {
    @Autowired
    UserMapper userMapper;
    @GetMapping("/get/users")
    public String getAllUsers(){
        List<User> users = userMapper.getAllUsers();
        return users.toString();
    }
}
