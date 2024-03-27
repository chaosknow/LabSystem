package com.example.labsystem.pojo;

import lombok.Data;

import java.util.List;
@Data
public class Right {
    private Integer id;
    private String title;
    private String icon;
    private String path;
    private List<Right> children;
}
