package com.chinasofti.blog.base.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class VerificationCodeController {
    @GetMapping("/code")
    public String code(HttpSession session){
        ShearCaptcha sc = CaptchaUtil.createShearCaptcha(140, 35, 4, 4);
        String code = sc.getCode();
        session.setAttribute("code",code);
        return code;
    }
}
