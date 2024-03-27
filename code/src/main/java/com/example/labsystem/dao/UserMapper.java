package com.example.labsystem.dao;

import com.example.labsystem.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface UserMapper {
    public List<User> getUserList(User user);

    void addUserList(User user);

    void updateUserList(User user);

    void deleteUser(Integer id);
}
