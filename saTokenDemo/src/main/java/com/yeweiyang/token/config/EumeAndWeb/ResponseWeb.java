package com.yeweiyang.token.config.EumeAndWeb;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Jay
 * @version V1.0
 * @Package com.shanghai.test1114.config
 * @date 2022/1/27 2:08 下午
 */
@Data
public class ResponseWeb<E> implements Serializable {

    private String code;
    private String message;
    private String subMessage;
    private E result;
    public ResponseWeb() {
    }

    public ResponseWeb(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseWeb(String code, String message, String subMessage) {
        this.code = code;
        this.message = message;
        this.subMessage = subMessage;
    }

    public ResponseWeb(String code, String message, E result) {
        this.code = code;
        this.message = message;
        this.result = result;
    }

    public ResponseWeb(String code, String message, String subMessage, E result) {
        this.code = code;
        this.message = message;
        this.subMessage = subMessage;
        this.result = result;
    }

    public boolean isSuccess() {
        return CommonCodeEnum.SUCCESS.getCode().equals(this.code);
    }

    public boolean fail() {
        return !this.isSuccess();
    }

    @Override
    public String toString() {
        return "ResponseWeb [code=" + this.code + ", message=" + this.message + ", subMessage=" + this.subMessage + ", result=" + JSON.toJSONString(this.result) + "]";
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public String getSubMessage() {
        return this.subMessage;
    }

    public E getResult() {
        return this.result;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSubMessage(String subMessage) {
        this.subMessage = subMessage;
    }

    public void setResult(E result) {
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof ResponseWeb)) {
            return false;
        } else {
            ResponseWeb<?> other = (ResponseWeb)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label59: {
                    Object this$code = this.getCode();
                    Object other$code = other.getCode();
                    if (this$code == null) {
                        if (other$code == null) {
                            break label59;
                        }
                    } else if (this$code.equals(other$code)) {
                        break label59;
                    }

                    return false;
                }

                Object this$message = this.getMessage();
                Object other$message = other.getMessage();
                if (this$message == null) {
                    if (other$message != null) {
                        return false;
                    }
                } else if (!this$message.equals(other$message)) {
                    return false;
                }

                Object this$subMessage = this.getSubMessage();
                Object other$subMessage = other.getSubMessage();
                if (this$subMessage == null) {
                    if (other$subMessage != null) {
                        return false;
                    }
                } else if (!this$subMessage.equals(other$subMessage)) {
                    return false;
                }

                Object this$result = this.getResult();
                Object other$result = other.getResult();
                if (this$result == null) {
                    if (other$result != null) {
                        return false;
                    }
                } else if (!this$result.equals(other$result)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof ResponseWeb;
    }

    @Override
    public int hashCode() {
        boolean PRIME = true;
        int result = 1;
        Object $code = this.getCode();
        result = result * 59 + ($code == null ? 43 : $code.hashCode());
        Object $message = this.getMessage();
        result = result * 59 + ($message == null ? 43 : $message.hashCode());
        Object $subMessage = this.getSubMessage();
        result = result * 59 + ($subMessage == null ? 43 : $subMessage.hashCode());
        Object $result = this.getResult();
        result = result * 59 + ($result == null ? 43 : $result.hashCode());
        return result;
    }
}
