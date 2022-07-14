package com.yeweiyang.token.Exception;

import com.yeweiyang.token.config.EumeAndWeb.OrgEnum;
import com.yeweiyang.token.config.EumeAndWeb.ResponseWeb;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Jay
 * @version V1.0
 * @Package com.shanghai.test1114.Exception
 * @date 2022/1/27 2:34 下午
 */
public final class ExceptionUtils {
    private static final Logger log = LoggerFactory.getLogger(ExceptionUtils.class);

    public ExceptionUtils() {
    }

    public static CommonException create(ResponseWeb<?> responseWeb) {
        log.info("responseWeb: {}", responseWeb);
        return new CommonException(responseWeb.getCode(), responseWeb.getMessage(), responseWeb.getSubMessage());
    }

    public static CommonException create(OrgEnum OrgEnum) {
        log.info("OrgEnum: {}", OrgEnum.toString());
        return new CommonException(OrgEnum.getCode(), OrgEnum.getMessage());
    }

    public static CommonException create(OrgEnum OrgEnum, Throwable throwable) {
        log.info("OrgEnum: {}", OrgEnum.toString());
        return new CommonException(OrgEnum.getCode(), OrgEnum.getMessage(), throwable);
    }

    public static CommonException create(OrgEnum OrgEnum, String subMessage) {
        log.info("OrgEnum: {}, SubMessage: {}", OrgEnum.toString(), subMessage);
        return new CommonException(OrgEnum.getCode(), OrgEnum.getMessage(), subMessage);
    }

    public static CommonException create(OrgEnum OrgEnum, String subMessage, Throwable throwable) {
        log.info("OrgEnum: {}, SubMessage: {}", OrgEnum.toString(), subMessage);
        return new CommonException(OrgEnum.getCode(), OrgEnum.getMessage(), subMessage, throwable);
    }
}
