/*
 *
 */
package com.fijo.boot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;

/**
 * 用途：针对不同项目，读取额外的特殊配置
 * 作者: zhangbo
 * 时间: 2018/5/28  18:04
 */
@Configuration
@PropertySources(value = {@PropertySource("classpath:extra.properties")})
public class ExtraConfig {
    @Autowired
    private Environment environment;

    public String getValue(String key) {
        return environment.getProperty(key);
    }
}
