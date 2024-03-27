package com.example.labsystem.pojo;

import lombok.Data;

@Data
public class Role {
    private Integer id;
    private String roleName;
    private String roleType;
    private String rights;
}
