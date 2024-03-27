package com.example.labsystem.controller;

import com.example.labsystem.service.UploadService;
import com.example.labsystem.utils.ResultOBJ;
import com.example.labsystem.utils.SYSConsstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.FileNotFoundException;


@RequestMapping("/adminapi/upload")
@RestController
public class UploadController {
    @Autowired
    private UploadService uploadService;

    /**
     * requestParam的file名称是前端给的数据名称
     * @param file
     * @return
     */
    @PostMapping
    public ResultOBJ upload(@RequestParam("file") MultipartFile file){
        try {
            uploadService.upload(file);
            return new ResultOBJ(SYSConsstant.CODE_SUCCESS,"上传成功");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return new ResultOBJ(SYSConsstant.CODE_ERROR,"上传失败");

        }
    }

}
