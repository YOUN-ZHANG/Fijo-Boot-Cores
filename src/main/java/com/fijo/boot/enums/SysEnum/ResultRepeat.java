package com.fijo.boot.enums.SysEnum;

import lombok.Getter;

@Getter
public enum ResultRepeat {
    RESULT_SUCCESS("2","success"),
    RESULT_ERROR("0","error"),
    RESULT_NO("3","success"),
    ;

    private String code;
    private String msg;

    ResultRepeat(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
