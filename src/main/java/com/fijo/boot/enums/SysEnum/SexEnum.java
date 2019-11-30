package com.fijo.boot.enums.SysEnum;

import lombok.Getter;

@Getter
public enum SexEnum {
    MEN(1,"男"),
    WOMEN(2,"女")
    ;

    private int code;
    private String msg;

    SexEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
