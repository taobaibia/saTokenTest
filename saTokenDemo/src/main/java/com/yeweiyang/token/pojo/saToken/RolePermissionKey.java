package com.yeweiyang.token.pojo.saToken;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 
 * 角色权限关联表
 */
@Data
@TableName(value = "role_permission")
public class RolePermissionKey implements Serializable {
    /**
     * 权限ID
     */
    private Long permissionId;
    /**
     * 角色ID
     */
    private Long roleId;

    private static final long serialVersionUID = 1L;
}