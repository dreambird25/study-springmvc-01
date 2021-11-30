package com.chinasofti.blog.back.bean;

import lombok.Data;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
//指定数据库表名
@Table(name = "t_article")

//声明属性名和表字段的对应形式 Style.normal表示字段和属性一致
@NameStyle(Style.normal)
public class Article {
    @Id//标志为主键
    private String aid;
    private String title;
    private String digest;
    private String content;
    private String cid;
    private String visit_count;
    private String create_time;
    private String update_time;
    private String is_hot;
    private String logo;
    private String uid;
    private String isOpen;
    private String thumbsUp;
    private String tagNames;
    private String isCommented;

}
