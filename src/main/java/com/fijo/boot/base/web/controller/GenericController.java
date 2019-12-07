/*
 *
 */
package com.fijo.boot.base.web.controller;

import com.fijo.boot.base.model.GenericModel;
import com.fijo.boot.base.service.IGenericService;
import com.fijo.boot.dto.ResultDto;
import com.fijo.boot.exceptions.AppException;
import com.fijo.boot.exceptions.Exceptions;
import com.fijo.boot.exceptions.ExceptionsMsgAlert;
import com.fijo.boot.exceptions.TransactionRollbackException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * 用途：通用Controller层
 * 作者: zhangbo
 * 时间: 2019/11/29  15:59
 */
@Slf4j
public class GenericController<T extends GenericModel, PK extends Serializable> {

    private IGenericService<T, PK> service;

//    public GenericController(Class<T> persistentClass,IGenericService<T, PK> service) {
//        this.service = service;
//        this.persistentClass = persistentClass;
//    }
    public GenericController(IGenericService<T, PK> service) {
        this.service = service;
    }

    //protected Class<T> persistentClass;

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

    @PostMapping(value = "/queryByparams")
    @ResponseBody
    public String query(Map<String, Object> params) throws Exception {
        try {
            List<T> result = this.service.queryAll(params);
            return ResultDto.SUCCESS("SUCCESS", result);
        }
        catch (Exception e){
            log.info("【查询】 /query, {}", e.getMessage());
            return ResultDto.ERROR(e.getMessage());
        }
    }

    @GetMapping(value = "/getById")
    @ResponseBody
    public String getById(PK id) throws Exception {
        try {
            T result = this.service.getById(id);
            return ResultDto.SUCCESS("SUCCESS", result);
        }
        catch (Exception e){
            log.info("【查询】 /getById, {}", e.getMessage());
            return ResultDto.ERROR(e.getMessage());
        }
    }


    @PostMapping(value = "/update")
    @ResponseBody
    public String update(T entity) throws Exception {
        try {
            int i = this.service.update(entity);
            return ResultDto.SUCCESS("SUCCESS", entity);
        }
        catch (NullPointerException var5) {
            this.log.error(Exceptions.getStackTraceAsString(var5));
            return ResultDto.ERROR("唯一性字段不能为空或重复");
        } catch (DataIntegrityViolationException var6) {
            this.log.error(Exceptions.getStackTraceAsString(var6));
            return ResultDto.ERROR("请检查约束条件");
        } catch (TransactionRollbackException var7) {
            this.log.error(Exceptions.getStackTraceAsString(var7));
            return ResultDto.ERROR("提交事务异常!");
        } catch (AppException var8) {
            this.log.error(Exceptions.getStackTraceAsString(var8));
            return ResultDto.ERROR("数据异常，请确认后重新尝试!");
        } catch (Exception var9) {
            this.log.error(Exceptions.getStackTraceAsString(var9));
            return ResultDto.ERROR("数据异常，请确认后重新尝试!");
        }
    }


    @PostMapping(value = "/batchUpdate")
    @ResponseBody
    public String batchUpdate(List<T> entitys) throws Exception {
        try {
            int i = this.service.batchUpdate(entitys);
            return ResultDto.SUCCESS("SUCCESS", entitys);
        }
        catch (NullPointerException var5) {
            this.log.error(Exceptions.getStackTraceAsString(var5));
            return ResultDto.ERROR("唯一性字段不能为空或重复");
        } catch (DataIntegrityViolationException var6) {
            this.log.error(Exceptions.getStackTraceAsString(var6));
            return ResultDto.ERROR("请检查约束条件");
        } catch (TransactionRollbackException var7) {
            this.log.error(Exceptions.getStackTraceAsString(var7));
            return ResultDto.ERROR("提交事务异常!");
        } catch (AppException var8) {
            this.log.error(Exceptions.getStackTraceAsString(var8));
            return ResultDto.ERROR("数据异常，请确认后重新尝试!");
        } catch (Exception var9) {
            this.log.error(Exceptions.getStackTraceAsString(var9));
            return ResultDto.ERROR("数据异常，请确认后重新尝试!");
        }
    }

    @PostMapping(value = "/insert")
    @ResponseBody
    public String insert(T entity) throws Exception {
        try {
            int i = this.service.insert(entity);
            return ResultDto.SUCCESS("SUCCESS", entity);
        }
        catch (NullPointerException var5) {
            this.log.error(Exceptions.getStackTraceAsString(var5));
            return ResultDto.ERROR("唯一性字段不能为空或重复");
        } catch (DataIntegrityViolationException var6) {
            this.log.error(Exceptions.getStackTraceAsString(var6));
            return ResultDto.ERROR("请检查约束条件");
        } catch (TransactionRollbackException var7) {
            this.log.error(Exceptions.getStackTraceAsString(var7));
            return ResultDto.ERROR("提交事务异常!");
        } catch (AppException var8) {
            this.log.error(Exceptions.getStackTraceAsString(var8));
            return ResultDto.ERROR("数据异常，请确认后重新尝试!");
        } catch (Exception var9) {
            this.log.error(Exceptions.getStackTraceAsString(var9));
            return ResultDto.ERROR("数据异常，请确认后重新尝试!");
        }
    }

    @PostMapping(value = "/batchInsert")
    @ResponseBody
    public String batchInsert(List<T> entitys) throws Exception {
        try {
            int i = this.service.batchInsert(entitys);
            return ResultDto.SUCCESS("SUCCESS", entitys);
        }
        catch (NullPointerException var5) {
            this.log.error(Exceptions.getStackTraceAsString(var5));
            return ResultDto.ERROR("唯一性字段不能为空或重复");
        } catch (DataIntegrityViolationException var6) {
            this.log.error(Exceptions.getStackTraceAsString(var6));
            return ResultDto.ERROR("请检查约束条件");
        } catch (TransactionRollbackException var7) {
            this.log.error(Exceptions.getStackTraceAsString(var7));
            return ResultDto.ERROR("提交事务异常!");
        } catch (AppException var8) {
            this.log.error(Exceptions.getStackTraceAsString(var8));
            return ResultDto.ERROR("数据异常，请确认后重新尝试!");
        } catch (Exception var9) {
            this.log.error(Exceptions.getStackTraceAsString(var9));
            return ResultDto.ERROR("数据异常，请确认后重新尝试!");
        }
    }

    @PostMapping(value = "/delete")
    @ResponseBody
    public String delete(T entity) throws Exception {
        try {
            int i = this.service.delete(entity);
            return ResultDto.SUCCESS("SUCCESS", i);
        }
        catch (NullPointerException var5) {
            this.log.error(Exceptions.getStackTraceAsString(var5));
            return ResultDto.ERROR("唯一性字段不能为空或重复");
        } catch (DataIntegrityViolationException var6) {
            this.log.error(Exceptions.getStackTraceAsString(var6));
            return ResultDto.ERROR("请检查约束条件");
        } catch (TransactionRollbackException var7) {
            this.log.error(Exceptions.getStackTraceAsString(var7));
            return ResultDto.ERROR("提交事务异常!");
        } catch (AppException var8) {
            this.log.error(Exceptions.getStackTraceAsString(var8));
            return ResultDto.ERROR("数据异常，请确认后重新尝试!");
        } catch (Exception var9) {
            this.log.error(Exceptions.getStackTraceAsString(var9));
            return ResultDto.ERROR("数据异常，请确认后重新尝试!");
        }
    }

    @GetMapping(value = "/deleteById")
    @ResponseBody
    public String deleteById(PK id) throws Exception {
        try {
            int i = this.service.deleteById(id);
            return ResultDto.SUCCESS("SUCCESS", i);
        }
        catch (NullPointerException var5) {
            this.log.error(Exceptions.getStackTraceAsString(var5));
            return ResultDto.ERROR("唯一性字段不能为空或重复");
        } catch (DataIntegrityViolationException var6) {
            this.log.error(Exceptions.getStackTraceAsString(var6));
            return ResultDto.ERROR("请检查约束条件");
        } catch (TransactionRollbackException var7) {
            this.log.error(Exceptions.getStackTraceAsString(var7));
            return ResultDto.ERROR("提交事务异常!");
        } catch (AppException var8) {
            this.log.error(Exceptions.getStackTraceAsString(var8));
            return ResultDto.ERROR("数据异常，请确认后重新尝试!");
        } catch (Exception var9) {
            this.log.error(Exceptions.getStackTraceAsString(var9));
            return ResultDto.ERROR("数据异常，请确认后重新尝试!");
        }
    }

    @PostMapping(value = "/batchDelete")
    @ResponseBody
    public String batchDelete(Set<PK> ids) throws Exception {
        try {
            int i = this.service.batchDelete(ids);
            return ResultDto.SUCCESS("SUCCESS", i);
        }
        catch (NullPointerException var5) {
            this.log.error(Exceptions.getStackTraceAsString(var5));
            return ResultDto.ERROR("唯一性字段不能为空或重复");
        } catch (DataIntegrityViolationException var6) {
            this.log.error(Exceptions.getStackTraceAsString(var6));
            return ResultDto.ERROR("请检查约束条件");
        } catch (TransactionRollbackException var7) {
            this.log.error(Exceptions.getStackTraceAsString(var7));
            return ResultDto.ERROR("提交事务异常!");
        } catch (AppException var8) {
            this.log.error(Exceptions.getStackTraceAsString(var8));
            return ResultDto.ERROR("数据异常，请确认后重新尝试!");
        } catch (Exception var9) {
            this.log.error(Exceptions.getStackTraceAsString(var9));
            return ResultDto.ERROR("数据异常，请确认后重新尝试!");
        }
    }






}
