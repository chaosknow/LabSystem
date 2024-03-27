package com.example.labsystem.service;

import com.example.labsystem.dao.LabMapper;
import com.example.labsystem.pojo.Lab;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LabServiceImpl implements LabService{
    @Autowired
    LabMapper labMapper;
    @Override
    public List<Lab> getLabList(Lab lab) {
        return labMapper.getLabList(lab);
    }

    @Override
    public void addLabList(Lab lab) {
        labMapper.addLab(lab);
    }

    @Override
    public void updateLabList(Lab lab) {
        labMapper.updateLabList(lab);
    }

    @Override
    public void deleteLabList(Integer id) {
        labMapper.deleteLabList(id);
    }
}
