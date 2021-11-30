package com.chinasofti.blog.back.controller;

import com.chinasofti.blog.back.bean.Article;
import com.chinasofti.blog.back.service.ArticleService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ArticleController {
    @Resource
    private ArticleService articleService;
    @RequestMapping("/article/list")
    @ResponseBody
    public PageInfo<Article> list(Integer page ,Integer pageSize,String title) {
        PageHelper.startPage(page, pageSize);
        ArrayList<Article> list = articleService.list(title);
        PageInfo<Article> articlePageInfo = new PageInfo<>(list);
        return articlePageInfo;
    }
}
