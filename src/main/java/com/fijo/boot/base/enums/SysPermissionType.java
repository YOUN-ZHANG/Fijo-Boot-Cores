/**
 *
 */
package com.fijo.boot.base.enums;

import lombok.Getter;
import lombok.Setter;

/**
 * 用途：系统权限类型
 * 作者: zhangbo
 * 时间: 2019/10/28  14:39
 */
public enum SysPermissionType implements GenericEnum {

    ROOT("根"),  MENU("访问路径"), BUTTON("按钮"), METHOD("执行方法"),MODULE("功能模块");

    @Setter @Getter
    private String value;

    SysPermissionType(String value) {
        this.value = value;
    }

}
