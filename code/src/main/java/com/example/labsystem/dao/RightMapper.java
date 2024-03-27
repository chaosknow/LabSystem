package com.example.labsystem.dao;

import com.example.labsystem.pojo.Right;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RightMapper {
    public List<Right> getRightList();

    void updateRightlist(Right right);

    void deleteRight(Integer id);
}
