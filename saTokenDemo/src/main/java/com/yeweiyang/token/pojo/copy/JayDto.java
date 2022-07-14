package com.yeweiyang.token.pojo.copy;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author jay
 */
@Data
public class JayDto  implements Serializable {
    private Long id;
    private Boolean delFlag2;
    private String userName;
    private String createUser;
    private Date createTime;
    private Date updateTime;
    private String updateUser;

    private List<FengLinDto> fengLinListDto;

}
