package com.example.labsystem.dao;

import com.example.labsystem.pojo.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface RoleMapper {
    public List<Role> getRoleList();


    void updateRolelist(Role role);

    void deleteRole(Integer id);
}
