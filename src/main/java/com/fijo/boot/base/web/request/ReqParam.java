/*
 *
 */
package com.fijo.boot.base.web.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 用途：用于对外提供接口的请求参数，适用于对外提供/anonymous/**路径的接口鉴权
 * 作者: zhangbo
 * 时间: 2018/8/22  11:45
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReqParam<T> {

    private int page;

    private int size;

    private String direction;

    private String properties;

    //请求数据
    private T data;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
