package com.chinasofti.blog.base.exception;

/**
 * message : 具体的错误消息
 * typeCode: 属于那个模块下的操作失败code
 */
public enum BlogEnum {
        USER_LOGIN_CODE("001-001","验证码输入错误"),
        USER_LOGIN_ACCOUNT("001-002","账号或密码错误"),
        USER_VERIFY_PWD("001-003","请输入正确的旧密码"),
    USER_UPDATE_VERIFY("001-004","新密码和确认密码不一致"),
    USER_UPDATE_FAIL("001-005","修改密码失败"),
    ARTICLE_UPDATE_ISOPEN("002-001","公开或私密更新失败"),
    ;
    private String typeCode;
    private String message;


    BlogEnum(String typeCode, String message) {
        this.message = message;
        this.typeCode = typeCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }
}
