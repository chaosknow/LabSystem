package com.example.labsystem.service;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@Service
public class UploadServiceImpl implements UploadService {
    @Override
    public void upload(MultipartFile file) throws FileNotFoundException {
        String fileName ="bg.jpg";
        String filePath=ResourceUtils.getURL("classpath:").getPath()+"static/upload/";
        String decodedPath = URLDecoder.decode(filePath, StandardCharsets.UTF_8);
//        System.out.println(decodedPath+fileName);
        File dist = new File(decodedPath + fileName);
//        System.out.println(dist);
        if (!dist.getParentFile().exists()){
            dist.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dist);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
