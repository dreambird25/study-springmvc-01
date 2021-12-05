package com.chinasofti.blog.base.controller;

import com.chinasofti.blog.base.util.FileUploadUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class FileUploadController {
    //上传图片
    @RequestMapping("/editorUpload")
    @ResponseBody
    public Map<String,Object> upload(
            @RequestParam(value = "editormd-image-file",required = false) MultipartFile img, HttpSession session){
        return FileUploadUtil.fileUpload(img,session);
    }
    //上传logo
    @RequestMapping("/fileUpload")
    @ResponseBody
    public Map<String,Object> fileUpload(MultipartFile img, HttpSession session){
        return FileUploadUtil.fileUpload(img,session);
    }
}
