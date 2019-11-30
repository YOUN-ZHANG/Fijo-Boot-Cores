package com.fijo.boot.enums.ModularEnum;

import lombok.Getter;

/**
 * 企业导入模板枚举类
 */
@Getter
public enum EntEnum {
    ENT("1", "工商(企业)"),
    ENT_INDIVIDUAL("2", "个体户");

    private String code;
    private String msg;

    EntEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static EntEnum getEnum(String code) {
        for (EntEnum entEnum : EntEnum.values()) {
            if (entEnum.getCode() == code) {
                return entEnum;
            }
        }
        return null;
    }
}
