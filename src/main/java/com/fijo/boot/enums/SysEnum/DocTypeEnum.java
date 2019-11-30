package com.fijo.boot.enums.SysEnum;

import lombok.Getter;

@Getter
public enum DocTypeEnum {
    DOC_GRANT("1","发放"),
    DOC_STAY_GRANT("2","待发放")
    ;

    private String code;
    private String msg;

    DocTypeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static DocTypeEnum getEnum(String code){
        for(DocTypeEnum statueEnum: DocTypeEnum.values()){
            if(statueEnum.getCode() == code){
                return statueEnum;
            }
        }
        return null;
    }
}
