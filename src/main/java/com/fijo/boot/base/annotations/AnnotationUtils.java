/*
 *
 */
package com.fijo.boot.base.annotations;

import com.google.common.collect.Maps;
import com.fijo.boot.base.exception.Exceptions;
import com.fijo.boot.constants.ApplicationConstants;
import lombok.Getter;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * 用途：注解工具类
 * 作者: zhangbo
 * 时间: 2018/2/28  19:33
 */
@Component
public class AnnotationUtils {

    @Getter
    private static Map<String, String> entityCnNameClassifyMap = Maps.newHashMap();

    @PostConstruct
    void loadEntityCnNames(){
        findAnnotatedEntityCnNameClasses();
    }

    /**
     * 读取被EntityCnName注解的类
     */
    private void findAnnotatedEntityCnNameClasses() {
        ClassPathScanningCandidateComponentProvider provider
                = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new AnnotationTypeFilter(EntityCnName.class));
        provider.findCandidateComponents(ApplicationConstants.PACKAGE_NAME).forEach(this::putEntityCnNameMetadata);
    }

    /**
     * 填充读取被EntityCnName注解的类集合
     * @param beanDef
     */
    private void putEntityCnNameMetadata(BeanDefinition beanDef) {
        try {
            Class<?> cl = Class.forName(beanDef.getBeanClassName());
            EntityCnName cnName = cl.getAnnotation(EntityCnName.class);
            entityCnNameClassifyMap.put(cl.getSimpleName(), cnName.name());
        } catch (Exception e) {
            Exceptions.printException(e);
        }
    }

}
