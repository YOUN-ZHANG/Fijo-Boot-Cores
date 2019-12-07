/**
 *
 */
package com.fijo.boot.base.enums;

import lombok.Getter;
import lombok.Setter;

/**
 * 用途：自定义字段属性类型
 * 作者: zhangbo
 * 时间: 2019/10/28  14:39
 */
public enum SysCustomFieldType implements GenericEnum, Comparable<SysCustomFieldType> {

    TEXT("文本"), TEXTAREA("长文本"), INTEGER("数字"),  PRICE("金额"), DATE("日期"), TIME("时间"), TIMESTAMP("时间戳"), ZDICT("数据字典");

    @Setter @Getter
    private String value;

    SysCustomFieldType(String value) {
        this.value = value;
    }

}
