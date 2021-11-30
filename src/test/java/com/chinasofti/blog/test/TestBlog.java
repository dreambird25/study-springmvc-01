package com.chinasofti.blog.test;

import com.chinasofti.blog.back.bean.Article;
import com.chinasofti.blog.back.bean.User;
import com.chinasofti.blog.back.mapper.ArticleMapper;
import com.chinasofti.blog.back.mapper.UserMapper;
import com.chinasofti.blog.base.exception.BlogEnum;
import com.chinasofti.blog.base.exception.BlogException;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

public class TestBlog {
    private BeanFactory beanFactory =
        new ClassPathXmlApplicationContext("spring/applicationContext.xml");
    private   ArticleMapper articleMapper = beanFactory.getBean("articleMapper", ArticleMapper.class);
    @Test
    public void test00(){
        Article article = new Article();
        article.setCid("74");
        article.setIs_hot("2");
        article.setContent("文章内容11");
        article.setDigest("文章摘要11");
        articleMapper.insert(article);
    }
    @Test
    public void test01(){
        Article article = new Article();
        article.setAid("1");
        article.setCid("71");
        article.setIs_hot("1");
        article.setContent("文章内容02");
        article.setDigest("文章摘要");
        articleMapper.updateByPrimaryKeySelective(article);
    }
    //测试删除
    @Test
    public void test02() throws ClassNotFoundException {
//        Example example = new Example(Class.forName("com.chinasofti.blog.back.bean.Article"));
//        example.createCriteria().andGreaterThanOrEqualTo("aid",3);//设置条件aid>=3
//        articleMapper.deleteByExample(example);
        Article article = new Article();
        article.setAid("1");
        article.setCid("71");
        article.setIs_hot("1");
        article.setContent("文章内容02");
        article.setDigest("文章摘要");
        articleMapper.deleteByPrimaryKey(article);
    }

    @Test
    public void test03(){
        Example example = new Example(Article.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andLike("content","%"+"内容"+"%");
        criteria.andGreaterThanOrEqualTo("aid","2");
        articleMapper.selectByExample(example);
    }
    @Test
    public void test04(){
        try {
            throw new BlogException(BlogEnum.USER_LOGIN_CODE);
        }catch (BlogException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
