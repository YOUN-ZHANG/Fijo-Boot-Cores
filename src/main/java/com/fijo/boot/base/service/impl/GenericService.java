package com.fijo.boot.base.service.impl;

import com.fijo.boot.base.model.GenericModel;
import com.fijo.boot.base.mapper.GenericMapper;
import com.fijo.boot.base.service.IGenericService;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;


@Slf4j
//@CacheConfig(cacheNames = ApplicationConstants.REDIS_DEFAULT_CACHE_PREFIX)
public class GenericService<T extends GenericModel,PK extends Serializable> implements IGenericService<T,PK>{

    private GenericMapper<T,PK> mapper;

    public GenericService(){}

    public GenericService ( GenericMapper<T, PK> genericMapper ) {
        this.mapper = genericMapper;
    }

    @Override
    public List<T> queryAll(T entity) {
        return mapper.queryAll(entity);
    }

    @Override
    public List<T> queryAll(Map<String, Object> params) {
        return mapper.queryAll(params);
    }

    @Override
    public T getById(PK id) {
        return mapper.getById(id);
    }

    @Override
    public int update(T entity) {
        return mapper.update(entity);
    }

    @Override
    public int updateById(PK id) {
        return mapper.updateById(id);
    }

    @Override
    public int batchUpdate(List<T> entitys) {
        return mapper.batchUpdate(entitys);
    }

    @Override
    public int insert(T entity) {
        return mapper.insert(entity);
    }

    @Override
    public int batchInsert(List<T> entitys) {
        return mapper.batchInsert(entitys);
    }

    @Override
    public int delete(T entity) {
        return mapper.delete(entity);
    }

    @Override
    public int deleteById(PK id) {
        return mapper.deleteById(id);
    }

    @Override
    public int batchDelete(Set<PK> ids) {
        return mapper.batchDelete(ids);
    }


}
