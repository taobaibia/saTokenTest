package com.yeweiyang.token.pojo.copy;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author jay
 */
@Data
@TableName(value = "jay")
public class Jay implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(name = "主键")
    private Long id;
    @ApiModelProperty(value = "删除标志，0，1")
    private Boolean delFlag;
    @ApiModelProperty(value = "姓名")
    private String name;
    @ApiModelProperty(value = "创建人")
    private String createUser;
    @ApiModelProperty(value = "修改人")
    private String updateUser;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

//    private List<FengLin> fengLinList;

}
