package com.yeweiyang.token.config.EumeAndWeb;

/**
 * @author Jay
 * @version V1.0
 * @Package com.shanghai.test1114.config
 * @date 2022/1/27 2:11 下午
 */
public final class ResponseUtils {
    public ResponseUtils() {
    }

    public static ResponseWeb<Void> success() {
        return create(CommonCodeEnum.SUCCESS);
    }

    public static ResponseWeb<Void> success(String subMessage) {
        return create((OrgEnum)CommonCodeEnum.SUCCESS, (String)subMessage);
    }

    public static <E> ResponseWeb<E> success(E result) {
        return (ResponseWeb<E>) create((OrgEnum)CommonCodeEnum.SUCCESS, (Object)result);
    }

    public static <E> ResponseWeb<E> success(String subMessage, E result) {
        return (ResponseWeb<E>) create((OrgEnum)CommonCodeEnum.SUCCESS, subMessage, (Object)result);
    }

    public static ResponseWeb<Void> exception() {
        return create(CommonCodeEnum.EXCEPTION);
    }

    public static ResponseWeb<Void> exception(String subMessage) {
        return create((OrgEnum)CommonCodeEnum.EXCEPTION, (String)subMessage);
    }

    public static <E> ResponseWeb<E> exception(String subMessage, E result) {
        return (ResponseWeb<E>) create((OrgEnum)CommonCodeEnum.EXCEPTION, subMessage, (Object)result);
    }

    public static <E> ResponseWeb<E> exception(String message, String subMessage, E result) {
        return create(CommonCodeEnum.EXCEPTION.getCode(), message, subMessage, result);
    }

    public static ResponseWeb<Void> create(OrgEnum OrgEnum) {
        return create(OrgEnum.getCode(), OrgEnum.getMessage());
    }

    public static ResponseWeb<Void> create(OrgEnum OrgEnum, String subMessage) {
        return create(OrgEnum.getCode(), OrgEnum.getMessage(), subMessage);
    }

    public static ResponseWeb<Void> create(String code, String message) {
        return new ResponseWeb(code, message);
    }

    public static ResponseWeb<Void> create(String code, String message, String subMessage) {
        return new ResponseWeb(code, message, subMessage);
    }

    public static <E> ResponseWeb<E> create(String code, String message, String subMessage, E result) {
        return new ResponseWeb(code, message, subMessage, result);
    }

    public static <E> ResponseWeb<E> create(OrgEnum OrgEnum, E result) {
        return new ResponseWeb(OrgEnum.getCode(), OrgEnum.getMessage(), result);
    }

    public static <E> ResponseWeb<E> create(OrgEnum OrgEnum, String subMessage, E result) {
        return new ResponseWeb(OrgEnum.getCode(), OrgEnum.getMessage(), subMessage, result);
    }
}
