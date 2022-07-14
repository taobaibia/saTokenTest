package com.yeweiyang.token.Exception;

import com.yeweiyang.token.config.EumeAndWeb.OrgEnum;

/**
 * @author Jay
 * @version V1.0
 * @Package com.shanghai.test1114.Exception
 * @date 2022/1/27 2:35 下午
 */
public class CommonException extends Exception{
    private static final long serialVersionUID = 946098281133332679L;
    private String code;
    private String subMessage;

    public CommonException(OrgEnum fastEnum) {
        this(fastEnum.getCode(), fastEnum.getMessage());
    }

    public CommonException(String code, String message) {
        super(message);
        this.code = "";
        this.subMessage = "";
        this.code = code;
    }

    public CommonException(String code, String message, String subMessage) {
        super(message);
        this.code = "";
        this.subMessage = "";
        this.code = code;
        this.subMessage = subMessage;
    }

    public CommonException(String code, String message, Throwable throwable) {
        super(message, throwable);
        this.code = "";
        this.subMessage = "";
        this.code = code;
    }

    public CommonException(String code, String message, String subMessage, Throwable throwable) {
        super(message, throwable);
        this.code = "";
        this.subMessage = "";
        this.code = code;
        this.subMessage = subMessage;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSubMessage() {
        return this.subMessage;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("CommonException [code=");
        builder.append(this.getCode());
        builder.append(", message=");
        builder.append(this.getMessage());
        builder.append(", subMessage=");
        builder.append(this.getSubMessage());
        builder.append("]");
        return builder.toString();
    }
}
