package com.fijo.boot.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户表
 */
@Data
public class ResultUserDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long uId; //用户id
    private String tenant;//租户编码
    private String orgCode;//组织编码
    private String groupId;//用户组id
    private String groupName;//用户组
    private String roleId;//角色id
    private String roleName;//角色
    private String uName;//用户名
    private String uNickName;//用昵称
    private String hrId;//对应员工id
    private String depId;//所属部门id
    private String depName;//所属部门
    private String phone;//联系方式
    private Integer userType;//用户类型
    private Integer uState;//用户状
    private String filePath; //文件保存路径

}
