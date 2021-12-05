package com.chinasofti.blog.back.controller;

import com.chinasofti.blog.back.bean.Article;
import com.chinasofti.blog.back.bean.Category;
import com.chinasofti.blog.back.bean.Tag;
import com.chinasofti.blog.back.bean.User;
import com.chinasofti.blog.back.service.ArticleService;
import com.chinasofti.blog.base.bean.ResultVo;
import com.chinasofti.blog.base.exception.BlogException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ArticleController {
    @Resource
    private ArticleService articleService;
    @RequestMapping("/article/list")
    @ResponseBody
    public PageInfo<Article> list(Integer page , Integer pageSize, String title, HttpSession session) {
        User user = (User) session.getAttribute("user");
        PageHelper.startPage(page, pageSize);
        ArrayList<Article> list = articleService.list(user.getUid(),title);
        PageInfo<Article> articlePageInfo = new PageInfo<>(list);
        return articlePageInfo;
    }
    @ResponseBody
    @RequestMapping("/article/isOpen")
    public ResultVo isOpen(String aid,String isOpen){
        //是否公开 0:不公开 1:公开
        ResultVo rv = new ResultVo();
        try{
            articleService.isOpen(aid,isOpen);
            rv.setOk(true);
            if("1".equals(isOpen)){
                rv.setMess("文章已公开");
            }else{
                rv.setMess("文章已私密");
            }
        }catch (BlogException e){
            rv.setMess(e.getMessage());
        }
        return rv;
    }
    @ResponseBody
    @RequestMapping("/article/queryCategory")
    public List<Category> queryCategory(){
        List<Category> list = articleService.queryCategory();
        return list;
    }
    @ResponseBody
    @RequestMapping("/article/queryTags")
    public List<Tag> queryTags(String cid){
        List<Tag> list = articleService.queryTags(cid);
        return list;
    }
}
