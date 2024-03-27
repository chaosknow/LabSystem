package com.example.labsystem.service;

import com.example.labsystem.pojo.Right;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface RightService {
    @Autowired

    public List<Right> getRightList();

    void updateRightlist(Right right);

    void deleteRight(Integer id);
}
