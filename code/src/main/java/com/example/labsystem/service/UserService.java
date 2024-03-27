package com.example.labsystem.service;

import com.example.labsystem.pojo.User;

import java.util.List;

public interface UserService {
    public List<User> getUserList(User user);

    void addUserList(User user);

    void updateUserList(User user);

    void deleteUser(Integer id);
}
