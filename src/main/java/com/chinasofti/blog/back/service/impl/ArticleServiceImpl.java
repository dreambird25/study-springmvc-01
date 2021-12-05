package com.chinasofti.blog.back.service.impl;

import com.chinasofti.blog.back.bean.Article;
import com.chinasofti.blog.back.bean.Category;
import com.chinasofti.blog.back.bean.Tag;
import com.chinasofti.blog.back.mapper.ArticleMapper;
import com.chinasofti.blog.back.mapper.CategoryMapper;
import com.chinasofti.blog.back.mapper.TagMapper;
import com.chinasofti.blog.back.service.ArticleService;
import com.chinasofti.blog.base.exception.BlogEnum;
import com.chinasofti.blog.base.exception.BlogException;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Resource
    private ArticleMapper articleMapper;
    @Resource
    private CategoryMapper categoryMapper;

    @Resource
    private TagMapper tagMapper;

    @Override
    public ArrayList<Article> list(String uid, String title) {
        Example example = new Example(Article.class);
        Example.Criteria criteria = example.createCriteria();
        //title不为空并且title为空字符串才拼接模糊查询条件
        if(title != null && !title.equals("")){
            criteria.andLike("title","%"+title+"%");
        }
        if(uid != null && "".equals(uid.trim())){//添加查询条件uid
            criteria.andEqualTo("uid",uid);
        }
        ArrayList<Article> list = (ArrayList<Article>) articleMapper.selectByExample(example);
        //查询所有栏目
        List<Category> categories = categoryMapper.selectAll();
        //把栏目的信息存在map中
        HashMap<String, String> map = new HashMap<>();
        for(Category category : categories ){
            map.put(category.getCid(),category.getCname());
        }
        //替换文章列表中的cid的值为栏目名
        for(Article article : list){
            String cid = article.getCid();
            String cname = map.get(cid);
            article.setCid(cname);
        }
        return list;
    }

    @Override
    public void isOpen(String aid, String isOpen) {
        Article article = new Article();
        article.setAid(aid);
        article.setIsOpen(isOpen);
        int i = articleMapper.updateByPrimaryKeySelective(article);
        if(i == 0){
            throw new BlogException(BlogEnum.ARTICLE_UPDATE_ISOPEN);
        }
    }

    @Override
    public List<Category> queryCategory() {
        List<Category> list = categoryMapper.selectAll();
        return list;
    }

    @Override
    public List<Tag> queryTags(String cid) {
        Example example = new Example(Tag.class);
        if(cid!=null&&!"".equals(cid)){
            example.createCriteria().andEqualTo("cid",cid);
        }
        List<Tag> tags = tagMapper.selectByExample(example);
        return tags;
    }
}
