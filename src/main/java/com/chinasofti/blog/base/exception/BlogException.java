package com.chinasofti.blog.base.exception;

public class BlogException extends RuntimeException{
    private BlogEnum blogEnum;
    public BlogException(BlogEnum blogEnum) {
        super(blogEnum.getMessage());
        this.blogEnum = blogEnum;
    }
}
