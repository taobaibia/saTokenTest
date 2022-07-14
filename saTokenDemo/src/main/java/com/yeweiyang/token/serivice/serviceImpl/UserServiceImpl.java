package com.yeweiyang.token.serivice.serviceImpl;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yeweiyang.token.Enums.JayEnum;
import com.yeweiyang.token.Exception.CommonException;
import com.yeweiyang.token.Exception.ExceptionUtils;
import com.yeweiyang.token.mapper.UserMapper;
import com.yeweiyang.token.pojo.saToken.User;
import com.yeweiyang.token.serivice.UserService;
import com.yeweiyang.token.utils.SaTokenPasswordUtils;
import com.yeweiyang.token.utils.StpUserUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jay
 * @version V1.0
 * @Package com.shanghai.test1114.serivice.serviceImpl
 * @date 2022/1/27 1:57 下午
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Value("${test.jay.private_key}")
    private String privateKey;

    @Value("${test.jay.public_key}")
    private String publicKey;
    /**
     * 注册用户
     */
    @Override
    public User doRegister(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        save(user);
        return user;
    }

    /**
     * 修改密码
     */
    @Override
    @SaCheckLogin
    public User doUpdatePassword(String username, String password) throws CommonException {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<User>();
        updateWrapper.lambda().eq(User::getUsername,username);
        User user = baseMapper.selectOne(updateWrapper);
        if (ObjectUtils.isEmpty(user)){
            throw ExceptionUtils.create(JayEnum.DATE_VALUE_NULL);
        }else {
            updateWrapper.lambda().set(User::getPassword,password);
        }
        update(updateWrapper);
        user = baseMapper.selectOne(updateWrapper);
        return user;
    }

    /**
    * 删除账号
    * */
    @Override
    public User doDeleteAccount(String username, String password) throws CommonException {
        String flag="删除失败！";
        QueryWrapper<User> wrapper = new QueryWrapper<User>();
        wrapper.lambda()
                .eq(User::getUsername,username)
                .eq(User::getPassword,password)
        ;
        User user = baseMapper.selectOne(wrapper);
        if (ObjectUtils.isEmpty(user)){
            throw ExceptionUtils.create(JayEnum.DATE_VALUE_NULL);
        }else{
            baseMapper.deleteById(user.getUserId());
            flag = "删除成功！";
        }
        log.info(flag+"账号是:     "+user.getUsername());
        return user;
    }
    /**
     * 所有账号
     */
    @Override
//    @SaCheckLogin
    public List<User> doSelectAccount() {
        List<User> list = baseMapper.selectList(null);
        return list;
    }
    /**
     * 登录账号
     */
    @Override
    public User doLogin(String username, String password) throws CommonException {
        QueryWrapper<User> wrapper = new QueryWrapper<User>();
        wrapper.lambda()
                .eq(User::getUsername,username)
        ;
        User user = baseMapper.selectOne(wrapper);

        if (ObjectUtils.isEmpty(user)) {
            throw ExceptionUtils.create(JayEnum.LOGIN_ERROR_NAME_NULL);
        }
        if (StringUtils.isBlank(user.getPassword())) {
            throw ExceptionUtils.create(JayEnum.LOGIN_PASSWORD_ERROR);
        }
        if (!password.equals(user.getPassword())){
            throw ExceptionUtils.create(JayEnum.LOGIN_PASSWORD_ERROR);
        }
        String encryptPassword = SaTokenPasswordUtils.setRsaEncryptByPublic(privateKey, publicKey, password);
        String encryptPasswordDB = SaTokenPasswordUtils.setRsaEncryptByPublic(privateKey, publicKey, user.getPassword());

        String rsaDecryptByPrivate = SaTokenPasswordUtils.getRsaDecryptByPrivate(privateKey, publicKey, encryptPassword);
        String rsaDecryptByPrivateDB = SaTokenPasswordUtils.getRsaDecryptByPrivate(privateKey, publicKey, encryptPasswordDB);
        System.out.println(encryptPasswordDB.equals(encryptPassword));
        System.out.println(rsaDecryptByPrivateDB.equals(rsaDecryptByPrivate));




        StpUtil.login(user.getUsername());
        return user;
    }
    /**
     * 登录账号user
     * 不同系统
     */
    @Override
    public User doLoginUser(String username, String password) throws CommonException {
        QueryWrapper<User> wrapper = new QueryWrapper<User>();
        wrapper.lambda()
                .eq(User::getUsername,username)
        ;
        User user = baseMapper.selectOne(wrapper);
        if (ObjectUtils.isEmpty(user)) {
            throw ExceptionUtils.create(JayEnum.LOGIN_ERROR_NAME_NULL);
        }
        if (StringUtils.isBlank(user.getPassword())) {
            throw ExceptionUtils.create(JayEnum.LOGIN_PASSWORD_ERROR);
        }
        if (!password.equals(user.getPassword())){
            throw ExceptionUtils.create(JayEnum.LOGIN_PASSWORD_ERROR);
        }
        StpUserUtil.login(user.getUsername(),StpUserUtil.TYPE);
        return user;
    }
}
