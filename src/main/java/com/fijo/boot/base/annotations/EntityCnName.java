/*
 *
 */
package com.fijo.boot.base.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用途：数据库实体中文名称
 * 作者: zhangbo
 * 时间: 2018/2/27  16:31
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface EntityCnName {
    String name();
}
