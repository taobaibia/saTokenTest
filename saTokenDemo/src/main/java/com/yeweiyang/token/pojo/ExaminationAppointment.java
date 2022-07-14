package com.yeweiyang.token.pojo;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 
 * 体检预约表
 */
@Data
@TableName(value = "examination_appointment")
public class ExaminationAppointment implements Serializable {
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 删除标志
     */
    private Boolean delFlag;

    /**
     * 预约号
     */
    private String appointmentNo;

    /**
     * 客户id
     */
    private String userId;

    /**
     * 预约状态 01未预约;02已预约;03取消预约;04重新预约;05预约完成;06体检完成
     */
    private String appointmentType;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private String sex;

    /**
     * 证件类型
     */
    private String certificateType;

    /**
     * 证件号
     */
    private String certificateNo;

    /**
     * 出生日期
     */
    private String birthday;

    /**
     * 年龄
     */
    private Byte age;

    /**
     * 民族
     */
    private String national;

    /**
     * 手机
     */
    private String telephone;

    /**
     * 渠道编码
     */
    private String channelCode;

    /**
     * 渠道名称
     */
    private String channelName;

    /**
     * 预约时间
     */
    private String appointmentTime;

    /**
     * 预约时间类型(上午,下午,全天)
     */
    private String appointmentTimeType;

    /**
     * 预约操作时间
     */
    private String appointmentCreatTime;

    /**
     * 体检套餐
     */
    private String examinationName;

    /**
     * 体检套餐编号
     */
    private String examinationCode;

    /**
     * 体检机构
     */
    private String examinationOrgName;

    /**
     * 体检机构编号
     */
    private String examinationOrgCode;

    /**
     * 分机构名
     */
    private String examinationOrgBranchName;

    /**
     * 分机构编号
     */
    private String examinationOrgBranchCode;

    /**
     * 分机构住址
     */
    private String examinationOrgBranchAddress;

    /**
     * 体检报告
     */
    private String medicalReport;

    /**
     * 修改原因
     */
    private String updateInfo;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 更新人
     */
    private String updateUser;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    private String img;

    private static final long serialVersionUID = 1L;
}