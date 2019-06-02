package com.fijo.boot.base.service;

import com.fijo.boot.base.model.LogicModel;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <strong>Title : 业务实体通用服务层</strong><br>
 * <strong>Description : 涉及业务实体的所有操作需要记录创建人信息和更新人信息</strong><br>
 * <strong>Create on : 2018/5/17</strong><br>
 * <strong>Modify on : 2018/5/17</strong><br>
 * <strong>Copyright (C) Ltd.</strong><br>
 *
 * @author LJW lijianwu@fijo.com.cn
 * @version <strong>V1.0.0</strong><br>
 * <strong>修改历史:</strong><br>
 * 修改人 修改日期 修改描述<br>
 * -------------------------------------------<br>
 */
public interface ILogicService<T extends LogicModel,PK extends Serializable> extends ISystemService<T,PK>{

    /**
     * 根据主键ID更新是否可用状态
     * @param enabled
     * @param id
     * @return
     */
    T updateEnable(PK id, boolean enabled);

    /**
     * 根据设定时间，定时删除
     * @param id
     * @param localDateTime
     */
    void scheduleLogicDelete(PK id, LocalDateTime localDateTime);

    /**
     * 根据设定时间，定时删除
     * @param entity
     * @param localDateTime
     */
    void scheduleLogicDelete(T entity, LocalDateTime localDateTime);

}
