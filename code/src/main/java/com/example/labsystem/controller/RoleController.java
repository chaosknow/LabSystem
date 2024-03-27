package com.example.labsystem.controller;

import com.example.labsystem.pojo.Right;
import com.example.labsystem.pojo.Role;
import com.example.labsystem.service.RoleService;
import com.example.labsystem.utils.ResultOBJ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adminapi/roles")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping
    public List<Role> getRoleList() {
        return roleService.getRoleList();
    }

    @PutMapping(value = "/{id}")
    public ResultOBJ updateRoleList(@PathVariable Integer id, @RequestBody Role role) {
        try {
            role.setId(id);
            roleService.updateRolelist(role);
            return ResultOBJ.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultOBJ.UPDATE_ERROR;
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResultOBJ deleteRole(@PathVariable Integer id){
        try {
            roleService.deleteRole(id);
            return ResultOBJ.DEL_SUCCESS;
        } catch (Exception e) {
            return ResultOBJ.DEL_ERROR;
        }

    }
}
