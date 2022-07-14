package com.yeweiyang.token.pojo.excelPojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class JayExcel implements Serializable {
    @Excel(name = "主键")
    private Long id;
    @Excel(name = "是否删除")
    private Boolean delFlag;
    @Excel(name = "姓名")
    private String name;
    @Excel(name = "创建人")
    private String createUser;
    @Excel(name = "创建时间")
    private Date createTime;
    @Excel(name = "修改时间")
    private Date updateTime;
    @Excel(name = "修改人")
    private String updateUser;

}
