package com.fijo.boot.enums.SysEnum;

import lombok.Getter;

@Getter
public enum HonoraryEnum {
    ENT_HONORARY("279","企业荣誉"),
    PER_HONORARY("280","个人荣誉")
    ;

    private String code;
    private String msg;

    HonoraryEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
