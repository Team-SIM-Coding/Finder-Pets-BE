package com.pet.petproject.mapper;

import com.pet.petproject.data.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    public List<User> getAllUsers();
    public User getUser(String nickname);
}
