package com.example.labsystem.service;

import com.example.labsystem.dao.RoleMapper;
import com.example.labsystem.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public List<Role> getRoleList() {
        return roleMapper.getRoleList();
    }

    @Override
    public void updateRolelist(Role role) {
        roleMapper.updateRolelist(role);
    }

    @Override
    public void deleteRole(Integer id) {
        roleMapper.deleteRole(id);
    }


}
