package com.yeweiyang.token.config.EumeAndWeb;

/**
 * @author Jay
 * @version V1.0
 * @Package com.shanghai.test1114.config
 * @date 2022/1/27 2:13 下午
 */
public enum CommonCodeEnum implements OrgEnum {
    SUCCESS("200000", "消息处理成功"),
    VALIDATE_ERROR("400000", "数据校验不合法"),
    ACCESS_PERM_DENIED("400001", "对不起,您没有访问权限"),
    CAPTCHA_VALIDATE_FAIL("400002", "图片验证码验证失败"),
    CAPTCHA_EXPIRE("400003", "图片验证码已失效"),
    PHARMACY_NOT_BIND("400004", "信息未绑定"),
    PHARMACY_NOT_EXIST("400005", "信息不存在"),
    PHONE_OR_PASSWORD_EMPTY("400006", "手机号或密码不能为空"),
    PHONE_OR_PASSWORD_ERROR("400007", "手机号或密码不正确"),
    PHONE_ERROR_FORMAT("400008", "手机号格式不正确"),
    VALIDATE_API_ERROR("400010", "数据校验不合法"),
    REQUEST_FREQUENTLY_ERROR("400011", "请求过于频繁"),
    EXCEPTION("500000", "系统繁忙,请稍后重试"),
    COMMON_EXCEPTION("500001", "消息处理异常"),
    DB_INSERT_EXCEPTION("500002", "数据插入异常"),
    DB_UPDATE_EXCEPTION("500003", "数据更新异常"),
    DB_SELECT_EXCEPTION("500004", "数据查询异常"),
    DB_KEY_DUPLICATE("500011", "主键或唯一性约束冲突")
    ;

    private String code;
    private String message;

    private CommonCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }


    public static CommonCodeEnum getEnumByCode(String code) {
        CommonCodeEnum[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            CommonCodeEnum commonCodeEnum = var1[var3];
            if (commonCodeEnum.getCode().equals(code)) {
                return commonCodeEnum;
            }
        }

        return null;
    }

    @Override
    public String toString() {
        return String.format("CommonCodeEnum name = %s code = %s message = %s", this.name(), this.code, this.message);
    }
}
