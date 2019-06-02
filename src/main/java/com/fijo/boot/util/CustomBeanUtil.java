/*
 *
 */
package com.fijo.boot.util;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.PropertyAccessorFactory;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

/**
 * <strong>Description : 基于Spring BeanUtils的自定义工具类</strong><br>
 * <strong>Create on : 2017年08月23日</strong><br>
 * <strong>Modify on : 2017年11月08日</strong><br>
 * <strong>Copyright Beijing Simbest Technology Ltd.</strong><br>
 *
 * @author zhangbo
 */
public class CustomBeanUtil extends BeanUtils {

    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    public static void copyPropertiesIgnoreNull(Object source, Object target) {
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }

    public static void copyTransientProperties(Object source, Object target) {
        BeanWrapper srcWrap = PropertyAccessorFactory.forBeanPropertyAccess(source);
        BeanWrapper trgWrap = PropertyAccessorFactory.forBeanPropertyAccess(target);
        Set<Field> fields = ObjectUtil.getEntityTransientField(source);
        fields.forEach(f -> trgWrap.setPropertyValue(f.getName(), srcWrap.getPropertyValue(f.getName())));
    }
}
