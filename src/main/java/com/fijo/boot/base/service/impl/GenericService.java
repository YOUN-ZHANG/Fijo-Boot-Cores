package com.fijo.boot.base.service.impl;

import com.fijo.boot.base.model.GenericModel;
import com.fijo.boot.base.mapper.GenericMapper;
import com.fijo.boot.base.service.IGenericService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.fijo.boot.util.DateUtil.getCurrentTimestamp;


@Slf4j
public class GenericService<T extends GenericModel,PK extends Serializable> implements IGenericService<T,PK>{

    private GenericMapper<T,PK> mapper;

    public GenericService(){}

    public GenericService ( GenericMapper<T, PK> genericMapper ) {
        this.mapper = genericMapper;
    }

    //@Cacheable(cacheNames = "EnabledCommonRules")
    @Override
    public List<T> queryAll(T entity) {
        return mapper.queryAll(entity);
    }

    @Override
    public List<T> queryAll(Map<String, Object> params) {
        return mapper.queryAll(params);
    }

    @Override
    public T getById(PK id) { return mapper.getById(id); }

    @Override
    @Transactional
    public int update(T entity) {
        entity.setUpdateTime(getCurrentTimestamp());
        return mapper.update(entity);
    }

    @Override
    @Transactional
    public int batchUpdate(List<T> entitys) {
        return mapper.batchUpdate(entitys);
    }

    @Override
    @Transactional
    public int insert(T entity) {
        entity.setEnabled("1");
        entity.setRemoved("0");
        entity.setCreateTime(getCurrentTimestamp());
        return mapper.insert(entity);
    }

    @Override
    @Transactional
    public int batchInsert(List<T> entitys) {
        return mapper.batchInsert(entitys);
    }

    @Override
    @Transactional
    public int delete(T entity) {
        return mapper.delete(entity);
    }

    @Override
    @Transactional
    public int deleteById(PK id) {
        return mapper.deleteById(id);
    }

    @Override
    @Transactional
    public int batchDelete(Set<PK> ids) {
        return mapper.batchDelete(ids);
    }



}
