/*
 *
 */
package com.fijo.boot.base.web.controller;

import com.fijo.boot.base.model.LogicModel;
import com.fijo.boot.base.service.ILogicService;
import com.fijo.boot.base.web.response.JsonResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;


/**
 * 用途：通用类对象控制器
 * 作者: zhangbo
 * 时间: 2018/6/7  15:59
 */
public class LogicController<T extends LogicModel, PK extends Serializable> extends GenericController<T, PK>{

    private ILogicService<T, PK> service;

    public LogicController(ILogicService<T, PK> service) {
        super(service);
        this.service = service;
    }


    @PostMapping(value = "/updateEnable")
    public JsonResponse updateEnable(@RequestParam PK id, @RequestParam boolean enabled) {
        return JsonResponse.success(service.updateEnable(id, enabled));
    }



}
