package com.yeweiyang.token.pojo.saToken;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 
 * 系统用户
 */
@Data
@TableName(value = "user")
public class User implements Serializable {
    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    private Long userId;

    /**
     * 部门名称
     */
    private Long deptId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 性别
     */
    private String gender;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 头像地址
     */
    private String avatarName;

    /**
     * 头像真实路径
     */
    private String avatarPath;

    /**
     * 密码
     */
    private String password;

    /**
     * 是否为admin账号
     */
    private Boolean isAdmin;

    /**
     * 状态：1启用、0禁用
     */
    private Long enabled;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 修改密码的时间
     */
    private Date pwdResetTime;

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