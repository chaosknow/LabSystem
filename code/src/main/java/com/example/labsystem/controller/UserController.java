package com.example.labsystem.controller;

import com.example.labsystem.config.JwtConfig;
import com.example.labsystem.pojo.User;
import com.example.labsystem.service.UserService;
import com.example.labsystem.utils.ResultOBJ;
import com.example.labsystem.utils.SYSConsstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adminapi/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtConfig jwtConfig;

    @GetMapping
    public List<User> getUserList(User user) {
        return userService.getUserList(user);
    }

    @PostMapping("/login")
    public ResultOBJ login(@RequestBody User user) {
        try {
            List<User> userList = userService.getUserList(user);
            if (userList.isEmpty()) {
                throw new RuntimeException();
            }
            User currentUser = userList.get(0);
            String token =jwtConfig.createToken(currentUser.getUsername());
            currentUser.setToken(token);
            return new ResultOBJ(SYSConsstant.CODE_SUCCESS, SYSConsstant.LOGIN_SUCCESS,currentUser );
        } catch (Exception e) {
            return new ResultOBJ(SYSConsstant.CODE_ERROR, SYSConsstant.LOGIN_ERROR);
        }
    }

    @PostMapping
    public ResultOBJ addUserList(@RequestBody User user) {
        try {
            userService.addUserList(user);
            return ResultOBJ.ADD_SUCCESS;
        } catch (Exception e) {
            return ResultOBJ.ADD_ERROR;
        }
    }

    @PutMapping(value = "/{id}")
    public ResultOBJ updateUserList(@PathVariable Integer id, @RequestBody User user) {
        try {
            user.setId(id);
            userService.updateUserList(user);
            return ResultOBJ.UPDATE_SUCCESS;
        } catch (Exception e) {
            return ResultOBJ.UPDATE_ERROR;
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResultOBJ deleteUser(@PathVariable Integer id) {
        try {
            userService.deleteUser(id);
            return ResultOBJ.DEL_SUCCESS;
        } catch (Exception e) {
            return ResultOBJ.DEL_ERROR;
        }

    }
}
