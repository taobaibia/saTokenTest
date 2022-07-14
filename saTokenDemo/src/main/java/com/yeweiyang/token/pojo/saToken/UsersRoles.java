package com.yeweiyang.token.pojo.saToken;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Jay
 * @version V1.0
 * @Package com.shanghai.test1114.pojo.saToken
 * @date 2022/1/28 1:27 下午
 */
@Data
@TableName(value = "users_roles")
public class UsersRoles {
    private Long userId;
    private Long roleId;
}
