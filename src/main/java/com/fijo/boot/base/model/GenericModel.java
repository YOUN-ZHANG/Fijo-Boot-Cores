package com.fijo.boot.base.model;


import lombok.Data;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

/**
 * 用途：通用实体
 * 作者: zhangbo
 * 时间: 2018/1/30  21:47
 */
@Data
@MappedSuperclass
public abstract class GenericModel implements Serializable, Comparable {

    @Column(name = "CREATE_TIME")
    private String createTime;//创建日期

    @Column(name = "CREATE_USER_NAME")
    private String createUserName;//创建人

    @Column(name = "CREATE_USER_ID")
    private Integer createUserId;//创建人ID

    @Column(name = "UPDATE_TIME")
    private String updateTime;//更新时间

    @Column(name = "UPDATE_USER_NAME")
    private String updateUserName;//更新人

    @Column(name = "UPDATE_USER_ID")
    private Integer updateUserId;//更新人ID

    @Column(name = "ENABLED")
    private String enabled;//是否有效

    @Column(name = "REMOVED")
    private String removed; //是否删除

    @Transient
    private String orderByClause;//排序问题
}
