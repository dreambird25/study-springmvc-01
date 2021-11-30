package com.chinasofti.blog.back.controller;

import com.chinasofti.blog.back.bean.User;
import com.chinasofti.blog.back.service.UserService;
import com.chinasofti.blog.base.bean.ResultVo;
import com.chinasofti.blog.base.exception.BlogException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Resource
    private UserService userService;
    @RequestMapping("/back/user/login")
    @ResponseBody
    public ResultVo login(User user, String code, HttpSession session){
        //获取正确的验证码
        String rightCode = (String) session.getAttribute("code");
        ResultVo resultVo = new ResultVo();
        try{
            resultVo.setOk(true);
            user = userService.login(user,code,rightCode);
            session.setAttribute("user",user);
        }catch (BlogException e){
            resultVo.setOk(false);
            resultVo.setMess(e.getMessage());
        }
        return resultVo;
    }

    @RequestMapping("/workbench/index")
    public String toIndex(){
        return "/workbench/index";
    }

    @RequestMapping("/user/loginOut")
    public String loginOut(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/login.jsp";
    }
    @ResponseBody
    @RequestMapping(value = "/user/verifyOldPwd",method = RequestMethod.POST)
    public ResultVo verifyOldPwd(HttpSession session,String oldPwd){
        ResultVo rv = new ResultVo();
        try{
            User user = (User) session.getAttribute("user");
            userService.verifyOldPwd(user.getPassword(),oldPwd);
            rv.setOk(true);
        }catch (BlogException e){
            rv.setMess(e.getMessage());
        }
        return rv;
    }
    @ResponseBody
    @RequestMapping("/user/updateUser")
    public ResultVo updateUser(User user,String new_password){
        ResultVo rv = new ResultVo();
        try{
            userService.updateUser(user,new_password);
            rv.setMess("更新成功，请重新登录");
            rv.setOk(true);
        }catch (BlogException e){
            rv.setMess(e.getMessage());
        }
        return rv;
    }
}
