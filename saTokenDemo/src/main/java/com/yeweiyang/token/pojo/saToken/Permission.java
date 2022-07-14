package com.yeweiyang.token.pojo.saToken;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 
 * 权限表
 */
@Data
@TableName(value = "permission")
public class Permission implements Serializable {
    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    private Long permissionId;
    /**
     * 名称
     */
    private String name;
    /**
     * 权限
     */
    private String permission;
    /**
     * 描述
     */
    private String description;
    /**
     * 创建者
     */
    private String createBy;
    /**
     * 更新者
     */
    private String updateBy;
    /**
     * 创建日期
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}