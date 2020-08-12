package com.fijo.boot.base.service;

import com.fijo.boot.base.model.GenericModel;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @ Author     ：zhangbo.
 * @ Date       ：Created in 23:41 2019/11/27
 * @ Description： 通用Service
 * @ Modified By：
 * @Version:
 */

public interface IGenericService <T extends GenericModel,PK extends Serializable>{

    /**
     * 通过实体类查询
     * @param entity
     * @return
     */
    List<T> queryAll(T entity);

    /**
     * 通过map查询
     * @param params
     * @return
     */
    List<T> queryAll(Map<String, Object> params);

    /**
     * 通过主键ID查询
     * @param id
     * @return
     */
    T getById(PK id);

    /**
     * 通过实体类更新
     * @param entity
     * @return
     */
    int update(T entity);

    /**
     * 批量更新
     * @param entitys
     * @return
     */
    int batchUpdate(List<T> entitys);

    /**
     * 插入数据
     * @param entity
     * @return
     */
    int insert(T entity);

    /**
     * 批量插入数据
     * @param entitys
     * @return
     */
    int batchInsert(List<T> entitys);

    /**
     * 通过条件删除
     * @param entity
     * @return
     */
    int delete(T entity);

    /**
     * 通过ID删除
     * @param id
     * @return
     */
    int deleteById(PK id);

    /**
     * 通过主键ID批量删除
     * @param ids
     * @return
     */
    int batchDelete(Set<PK> ids);

}
