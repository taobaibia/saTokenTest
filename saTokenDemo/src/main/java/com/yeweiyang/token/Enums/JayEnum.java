package com.yeweiyang.token.Enums;

import com.yeweiyang.token.config.EumeAndWeb.OrgEnum;

/**
 * @author Jay
 * @version V1.0
 * @Package com.shanghai.test1114.Enums
 * @date 2022/1/27 2:39 下午
 */
@SuppressWarnings("all")
public enum JayEnum implements OrgEnum {

    PARAMER_VALUE_NULL("JayP0001","缺少必要请求参数"),
    DATE_VALUE_NULL("JayP0002","数据不存在"),
    LOGIN_ERROR_NAME_NULL("JayP0003","登录失败，用户不存在"),
    LOGIN_PASSWORD_ERROR("JayP0004","登录失败，密码错误"),
    ;


    private String code;
    private String message;

    private JayEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public static JayEnum getEnumByCode(String code) {
        for (JayEnum epCoreAdminEnum : JayEnum.values()) {
            if (epCoreAdminEnum.getCode().equals(code)) {
                return epCoreAdminEnum;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return String.format("JayEnum name = %s code = %s message = %s", this.name(), code, message);
    }
}
