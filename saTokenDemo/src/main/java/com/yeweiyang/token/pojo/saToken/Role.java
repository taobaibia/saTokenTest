package com.yeweiyang.token.pojo.saToken;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 
 * 角色表
 */
@TableName(value = "role")
@Data
public class Role implements Serializable {
    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    private Long roleId;
    /**
     * 名称
     */
    private String name;
    /**
     * 角色级别
     */
    private Integer level;
    /**
     * 描述
     */
    private String description;
    /**
     * 数据权限
     */
    private String dataScope;
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