package com.yeweiyang.token.serivice;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yeweiyang.token.Exception.CommonException;
import com.yeweiyang.token.pojo.saToken.User;

import java.util.List;

/**
 * @author Jay
 * @version V1.0
 * @Package com.shanghai.test1114.serivice
 * @date 2022/1/27 1:59 下午
 */
public interface UserService extends IService<User> {
    /**
     * 注册账号
     */
    User doRegister(String username, String password);

    /**
     * 修改密码
     */
    User doUpdatePassword(String username, String password) throws CommonException;

    /**
     * 删除账号
     */
    User doDeleteAccount(String username, String password) throws CommonException;
    /**
     * 所有账号
     */
    List<User> doSelectAccount();

    /**
    * 登录账号
    * */
    User doLogin(String username, String password) throws CommonException;
    /**
    * 登录账号user系统
    * */
    User doLoginUser(String username, String password) throws CommonException;
}
