/*
 *
 */
package com.fijo.boot.base.web.controller;

import com.fijo.boot.base.exception.GlobalExceptionRegister;
import com.fijo.boot.base.model.GenericModel;
import com.fijo.boot.base.repository.Condition;
import com.fijo.boot.base.service.IGenericService;
import com.fijo.boot.base.web.response.JsonResponse;
import com.fijo.boot.util.CustomBeanUtil;
import com.fijo.boot.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Map;


/**
 * 用途：通用类对象控制器
 * 作者: zhangbo
 * 时间: 2018/6/7  15:59
 */
@Slf4j
public class GenericController<T extends GenericModel, PK extends Serializable> {

    private IGenericService<T, PK> service;

    public GenericController(IGenericService<T, PK> service) {
        this.service = service;
    }

    @PostMapping(value = "/findById")
    public JsonResponse findById(@RequestParam PK id) {
        return JsonResponse.success(service.findById(id));
    }

    @PostMapping(value = "/findAll")
    public JsonResponse findAll(@RequestParam(required = false, defaultValue = "1") int page, //
                                @RequestParam(required = false, defaultValue = "10") int size, //
                                @RequestParam(required = false) String direction, //
                                @RequestParam(required = false) String properties,
                                @RequestBody T o) {
        //获取分页规则, page第几页 size每页多少条 direction升序还是降序 properties排序规则（属性名称）
        Pageable pageable = service.getPageable(page, size, direction, properties);
        // 获取查询条件
        Condition condition = new Condition();
        Map<String, Object> params = ObjectUtil.getEntityPersistentFieldValueExceptId(o);
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            condition.eq(entry.getKey(), entry.getValue());
        }
        Specification<T> specification = service.getSpecification(condition);
        // 获取查询结果
        Page<T> pages = service.findAll(specification, pageable);
        return JsonResponse.success(pages);
    }

    @PostMapping(value = "/findAllNoPage")
    public JsonResponse findAllNoPage(@RequestBody T o) {
        // 获取查询条件
        Condition condition = new Condition();
        Map<String, Object> params = ObjectUtil.getEntityPersistentFieldValueExceptId(o);
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            condition.eq(entry.getKey(), entry.getValue());
        }
        Specification<T> specification = service.getSpecification(condition);
        Iterable<T> datas = service.findAllNoPage(specification);
        return JsonResponse.success(datas);
    }

    @PostMapping(value = "/create")
    public JsonResponse create(@RequestBody T o) {
        try {
            o = service.insert(o);
            return JsonResponse.success(o);
        } catch (Exception e) {
            return GlobalExceptionRegister.returnErrorResponse(e);
        }
    }

    @PostMapping(value = "/update")
    public JsonResponse update(@RequestBody T newObj) {
        T oldObj = service.findById((PK) ObjectUtil.getEntityIdVaue(newObj));
        CustomBeanUtil.copyPropertiesIgnoreNull(newObj, oldObj);
        try {
            oldObj = service.update(oldObj);
            return JsonResponse.success(oldObj);
        } catch (Exception e) {
            return GlobalExceptionRegister.returnErrorResponse(e);
        }
    }

    @PostMapping(value = "/deleteById")
    public JsonResponse deleteById(@RequestParam PK id) {
        try {
            service.deleteById(id);
            return JsonResponse.defaultSuccessResponse();
        } catch (Exception e) {
            return GlobalExceptionRegister.returnErrorResponse(e);
        }
    }

    @PostMapping(value = "/delete")
    public JsonResponse delete(@RequestBody T o) {
        try {
            service.delete(o);
            return JsonResponse.defaultSuccessResponse();
        } catch (Exception e) {
            return GlobalExceptionRegister.returnErrorResponse(e);
        }
    }

    @PostMapping(value = "/deleteAllByIds")
    public JsonResponse deleteAllByIds(@RequestBody PK[] ids) {
        try {
            service.deleteAllByIds(Arrays.asList(ids));
            return JsonResponse.defaultSuccessResponse();
        } catch (Exception e) {
            return GlobalExceptionRegister.returnErrorResponse(e);
        }
    }

}
