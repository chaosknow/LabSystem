package com.example.labsystem;

import com.example.labsystem.config.JwtConfig;
import com.example.labsystem.dao.LabMapper;
import com.example.labsystem.dao.RightMapper;
import com.example.labsystem.dao.RoleMapper;
import com.example.labsystem.dao.UserMapper;
import com.example.labsystem.pojo.Lab;
import com.example.labsystem.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LabsystemApplicationTests {
    @Autowired
    private JwtConfig jwtConfig;
    @Test
    void contextLoads() {
        String token =jwtConfig.createToken("admin");
        System.out.println(token);
        System.out.println(jwtConfig.getSubject(token));
    }

}
