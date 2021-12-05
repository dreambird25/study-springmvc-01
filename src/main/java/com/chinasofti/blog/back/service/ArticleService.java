package com.chinasofti.blog.back.service;

import com.chinasofti.blog.back.bean.Article;
import com.chinasofti.blog.back.bean.Category;
import com.chinasofti.blog.back.bean.Tag;

import java.util.ArrayList;
import java.util.List;

public interface ArticleService{

    ArrayList<Article> list(String uid, String title);

    void isOpen(String aid, String  isOpen);

    List<Category> queryCategory();

    List<Tag> queryTags(String cid);
}
