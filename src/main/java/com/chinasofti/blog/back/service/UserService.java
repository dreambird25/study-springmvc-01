package com.chinasofti.blog.back.service;

import com.chinasofti.blog.back.bean.User;

public interface UserService {

    User login(User user, String code, String rightCode);

    void verifyOldPwd(String password, String oldPwd);

    void updateUser(User user, String new_password);
}
