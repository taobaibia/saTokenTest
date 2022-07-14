package com.yeweiyang.token.response;

import lombok.Data;

/**
 * @author Jay
 * @version V1.0
 * @Package com.shanghai.test1114.response
 * @date 2022/1/27 3:38 下午
 */
@Data
public class UserResp {
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
}
