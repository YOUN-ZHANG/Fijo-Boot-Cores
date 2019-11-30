package com.fijo.boot.enums.SysEnum;

public enum SysLogEnum {
    query("查询"),
    create("新增"),
    update("更新"),
    delete("删除"),
    login("登录"),
    logout("退出");

    private String value;

    SysLogEnum(String value) {
        this.value = value;
    }
}
