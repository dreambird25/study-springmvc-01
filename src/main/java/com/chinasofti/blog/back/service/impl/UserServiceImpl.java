package com.chinasofti.blog.back.service.impl;

import com.chinasofti.blog.back.bean.User;
import com.chinasofti.blog.back.mapper.UserMapper;
import com.chinasofti.blog.back.service.UserService;
import com.chinasofti.blog.base.exception.BlogEnum;
import com.chinasofti.blog.base.exception.BlogException;
import com.chinasofti.blog.base.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
private UserMapper userMapper;
    @Override
    public User login(User user, String code, String rightCode) {
        //校验验证码
        if(!rightCode.equalsIgnoreCase(code)){
            throw new BlogException(BlogEnum.USER_LOGIN_CODE);
        }
        //对用户的密码进行加密
        user.setPassword(MD5Util.getMD5(user.getPassword()));
        //账号密码校验
        List<User> users = userMapper.select(user);
        //登录失败
        if(users.size() == 0){
            throw new BlogException(BlogEnum.USER_LOGIN_ACCOUNT);
        }
        //登录成功
        return users.get(0);
    }

    @Override
    public void verifyOldPwd(String password, String oldPwd) {
        //对旧密码密码进行加密
        oldPwd = MD5Util.getMD5(oldPwd);
        //验证密码
        if(!password.equals(oldPwd)){
            throw new BlogException(BlogEnum.USER_VERIFY_PWD);
        }
    }

    @Override
    public void updateUser(User user, String new_password) {
        //验证新密码和确认密码
        if(!user.getPassword().equals(new_password)){
            throw new BlogException(BlogEnum.USER_UPDATE_VERIFY);
        }
        //更新user
        //新密码进行加密
        user.setPassword(MD5Util.getMD5(new_password));
        int count = userMapper.updateByPrimaryKeySelective(user);
        if(count == 0){
            throw new BlogException(BlogEnum.USER_UPDATE_FAIL);
        }
    }
}
