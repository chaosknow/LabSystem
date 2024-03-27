package com.example.labsystem.service;

import com.example.labsystem.dao.UserMapper;
import com.example.labsystem.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;
    @Override
    public List<User> getUserList(User user) {
        return userMapper.getUserList(user);
    }

    @Override
    public void addUserList(User user) {
        userMapper.addUserList(user);
    }

    @Override
    public void updateUserList(User user) {
        userMapper.updateUserList(user);
    }

    @Override
    public void deleteUser(Integer id) {
        userMapper.deleteUser(id);
    }


}
