package com.example.labsystem.controller;

import com.example.labsystem.pojo.Right;
import com.example.labsystem.service.RightService;
import com.example.labsystem.utils.ResultOBJ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adminapi/rights")
public class RightController {
    @Autowired
    private RightService rightService;

    @GetMapping
    public List<Right> getRightList() {
        return rightService.getRightList();
    }

    @PutMapping(value = "/{id}")
    public ResultOBJ updateRightList(@PathVariable Integer id, @RequestBody Right right) {
        try {
            right.setId(id);
            rightService.updateRightlist(right);
            return ResultOBJ.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultOBJ.UPDATE_ERROR;
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResultOBJ deleteRight(@PathVariable Integer id){
        try {
            rightService.deleteRight(id);
            return ResultOBJ.DEL_SUCCESS;
        } catch (Exception e) {
            return ResultOBJ.DEL_ERROR;
        }

    }

}
