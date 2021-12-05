package com.chinasofti.blog.base.bean;

import lombok.Data;

/**
 * 给客户端返回结果bean对象
 */
@Data
public class ResultVo {
private String mess;//消息
private boolean isOk;//操作是否成功
}
