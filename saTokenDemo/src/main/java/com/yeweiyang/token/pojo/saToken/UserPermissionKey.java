package com.yeweiyang.token.pojo.saToken;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 
 * 账号权限关联表
 */
@Data
@TableName(value = "user_permission")
public class UserPermissionKey implements Serializable {
    /**
     * 权限ID
     */
    private Long permissionId;
    /**
     * 角色ID
     */
    private Long userId;

    private static final long serialVersionUID = 1L;
}