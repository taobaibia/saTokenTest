package com.yeweiyang.token.config;

import cn.dev33.satoken.listener.SaTokenListener;
import cn.dev33.satoken.stp.SaLoginModel;
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
public class MySaTokenListener implements SaTokenListener {
    /** 每次登录时触发 */
    @Override
    public void doLogin(String loginType, Object loginId, SaLoginModel loginModel) {
        System.out.println("登录了----------");
        // ...
    }
    /** 每次注销时触发 */
    @Override
    public void doLogout(String loginType, Object loginId, String tokenValue) {
        System.out.println("注销了----------");
        // ...
    }
    /** 每次被踢下线时触发 */
    @Override
    public void doKickout(String loginType, Object loginId, String tokenValue) {
        System.out.println("踢下线了----------");
        // ...
    }
    /** 每次被顶下线时触发 */
    @Override
    public void doReplaced(String loginType, Object loginId, String tokenValue) {
        System.out.println("顶下线了----------");
        // ...
    }
    /** 每次被封禁时触发 */
    @Override
    public void doDisable(String loginType, Object loginId, long disableTime) {
        System.out.println("封号了----------");
        // ...
    }
    /** 每次被解封时触发 */
    @Override
    public void doUntieDisable(String loginType, Object loginId) {
        System.out.println("解封了----------");
        // ...
    }
    /** 每次创建Session时触发 */
    @Override
    public void doCreateSession(String id) {
        System.out.println("创建session了----------");
        // ...
    }
    /** 每次注销Session时触发 */
    @Override
    public void doLogoutSession(String id) {
        System.out.println("注销session了----------");
        // ...
    }
}
