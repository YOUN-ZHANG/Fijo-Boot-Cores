package com.fijo.boot.enums.SysEnum;

import lombok.Getter;

@Getter
public enum DocEnum {
    DOC_WITHIN("1","内部通知"),
    DOC_EXT("2","外部通知")
    ;

    private String code;
    private String msg;

    DocEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static  DocEnum getEnum(String code){
        for(DocEnum statueEnum: DocEnum.values()){
            if(statueEnum.getCode() == code){
                return statueEnum;
            }
        }
        return null;
    }
}
