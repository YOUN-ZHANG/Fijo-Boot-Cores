/*
 *
 */
package com.fijo.boot.base.web.controller;

import com.fijo.boot.base.model.GenericModel;
import com.fijo.boot.base.service.IGenericService;
import com.fijo.boot.dto.ResultDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
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

    @PostMapping(value = "/query")
    @ResponseBody
    public String query(T entity) throws Exception {
        try {
            List<T> result = this.service.queryAll(entity);
            return ResultDto.SUCCESS("SUCCESS", result);
        }
        catch (Exception e){
            log.info("【查询】 /query, {}", e.getMessage());
            return ResultDto.ERROR(e.getMessage());
        }

    }

}
