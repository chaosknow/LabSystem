package com.example.labsystem.service;

import com.example.labsystem.dao.RightMapper;
import com.example.labsystem.pojo.Right;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RightServiceImpl implements RightService{
    @Autowired
    private RightMapper rightMapper;
    @Override
    public List<Right> getRightList() {
        return rightMapper.getRightList();
    }

    @Override
    public void updateRightlist(Right right) {
        rightMapper.updateRightlist(right);
    }

    @Override
    public void deleteRight(Integer id) {
        rightMapper.deleteRight(id);
    }
}
