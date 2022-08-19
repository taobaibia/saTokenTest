package com.yeweiyang.token.config;

import cn.dev33.satoken.listener.SaTokenListener;
import cn.dev33.satoken.stp.SaLoginModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Jay
 * @version V1.0
 * @Package com.shanghai.test1114.config
 * @date 2022/1/29 2:33 下午
 */
/**
 * 自定义侦听器的实现
 */
@Component
@Slf4j
public class MySaTokenListener implements SaTokenListener {
    /** 每次登录时触发 */
    @Override
    public void doLogin(String loginType, Object loginId, SaLoginModel loginModel) {
        log.info("登录了----------{},{},{}",loginType,loginId,loginModel);
        // ...
    }
    /** 每次注销时触发 */
    @Override
    public void doLogout(String loginType, Object loginId, String tokenValue) {
        log.info("账号:注销了----------{},{},{}",loginType,loginId,tokenValue);
        // ...
    }
    /** 每次被踢下线时触发 */
    @Override
    public void doKickout(String loginType, Object loginId, String tokenValue) {
        log.info("账号:被踢下线----------{},{},{}",loginType,loginId,tokenValue);
        // ...
    }
    /** 每次被顶下线时触发 */
    @Override
    public void doReplaced(String loginType, Object loginId, String tokenValue) {
        log.info("账号:被顶号----------{},{},{}",loginType,loginId,tokenValue);
        // ...
    }
    /** 每次被封禁时触发 */
    @Override
    public void doDisable(String loginType, Object loginId, long disableTime) {
        log.info("账号:被封禁----------{},{},{}",loginType,loginId,disableTime);
        // ...
    }
    /** 每次被解封时触发 */
    @Override
    public void doUntieDisable(String loginType, Object loginId) {
        log.info("账号:被解封----------{},{}",loginType,loginId);
        // ...
    }
    /** 每次创建Session时触发 */
    @Override
    public void doCreateSession(String id) {
        log.info("账号:创建了session----------{}",id);
        // ...
    }
    /** 每次注销Session时触发 */
    @Override
    public void doLogoutSession(String id) {
        log.info("账号:注销了session了----------{}",id);
        // ...
    }
}
