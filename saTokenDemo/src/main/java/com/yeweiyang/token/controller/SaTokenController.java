package com.yeweiyang.token.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.yeweiyang.token.Enums.JayEnum;
import com.yeweiyang.token.Exception.CommonException;
import com.yeweiyang.token.Exception.ExceptionUtils;
import com.yeweiyang.token.config.EumeAndWeb.ResponseUtils;
import com.yeweiyang.token.config.EumeAndWeb.ResponseWeb;
import com.yeweiyang.token.pojo.saToken.User;
import com.yeweiyang.token.serivice.UserService;
import com.yeweiyang.token.serivice.serviceImpl.StpInterfaceImpl;
import com.yeweiyang.token.utils.StpUserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Jay
 * @version V1.0
 * @Package com.shanghai.test1114.controller
 * @date 2022/1/21 3:40 下午
 * 权限认证
 */
@RestController
@RequestMapping("/user")
@Api(tags = "权限:认证管理模块")
public class SaTokenController {

    @Autowired
    private StpInterfaceImpl stpInterfaceImpl;
    @Autowired
    private UserService userService;

    /**
     * 测试登录，
     */
    @GetMapping("/doLogin")
    @ApiOperation("登录")
    public ResponseWeb<User> doLogin(String username, String password) throws CommonException {
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            throw ExceptionUtils.create(JayEnum.PARAMER_VALUE_NULL);
        }
        User user = userService.doLogin(username, password);
        System.out.println("获取账号的tokenValue值--------------->" + StpUtil.getTokenValueByLoginId(username));
        System.out.println("获取账号的Session对象：若🈚️返回null------------->" + StpUtil.getSessionByLoginId(username, false));
        System.out.println("获取账号的Session对象------------->" + StpUtil.getSessionByLoginId(username));
        System.out.println("获取账号是否有指定角色--------------->" + StpUtil.hasRole(username, "admin"));
        System.out.println("获取账号是否有指定权限--------------->" + StpUtil.hasPermissionAnd("save", "select", "delete", "update"));
        return ResponseUtils.success(user);
    }

    /**
     * 测试登录，
     */
    @GetMapping("/doLoginUser")
    @ApiOperation("登录User账号系统")
    public ResponseWeb<User> doLoginUser(String username, String password) throws CommonException {
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            throw ExceptionUtils.create(JayEnum.PARAMER_VALUE_NULL);
        }
        User user = userService.doLoginUser(username, password);
        System.out.println("获取账号的tokenValue值：" + StpUtil.getTokenValueByLoginId(username));
        System.out.println("获取账号的Session对象：若🈚️返回null：" + StpUtil.getSessionByLoginId(username, false));
        System.out.println("获取账号的Session对象：" + StpUtil.getSessionByLoginId(username));
        System.out.println("获取账号是否有指定角色：" + StpUtil.hasRole(username, "admin"));
        System.out.println("获取账号是否有指定权限：" + StpUtil.hasPermissionAnd("save", "select", "delete", "update"));
        return ResponseUtils.success(user);
    }

    /**
     * 退出登录，
     */
    @GetMapping("/doLogout")
    @ApiOperation("注销")
    public SaResult doLogout() {
        StpUtil.logout();
        return SaResult.ok("退出登录！");
    }

    /**
     * 踢下线
     */
    @GetMapping("/doKickout")
    @ApiOperation("踢下线")
    public SaResult doKickout(String RoolId) {
        System.out.println("被踢的账号:=========>" + RoolId);
        StpUtil.kickout(RoolId);
        return SaResult.ok("您已下线！");
    }

    /**
     * 封禁账号
     * 对于正在登录的账号，对其账号封禁时并不会使其立刻注销
     * 如果需要将其封禁后立即掉线，可采取先踢再封禁的策略，
     */
    @GetMapping("/doDisable")
    @ApiOperation("封禁账号")
    public SaResult doDisable(String RoolId, Long disableTime) {
        System.out.println("被封禁的账号:=========>   " + RoolId + "     封禁时长:=========>  " + disableTime);
        StpUtil.disable(RoolId, disableTime);
        return SaResult.ok("你已被封禁！");
    }

    /**
     * 查询登录状态，
     */
    @GetMapping("/isLogin")
    @ApiOperation("查看登录状态与token")
    public SaResult isLogin() {
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        return SaResult.ok("当前会话是否登录：" + StpUtil.isLogin()).setData(tokenInfo);
    }

    /**
     * 查看账号是否被封禁，
     */
    @GetMapping("/isDisable")
    @ApiOperation("查看账号是否被封禁")
    public SaResult isDisable(String RoolId) {
        return SaResult.ok("当前账号是否封禁：" + StpUtil.isDisable(RoolId));
    }

    /**
     * 查看账号剩余封禁时间，
     */
    @GetMapping("/isGetDisableTime")
    @ApiOperation("查看账号剩余封禁时间")
    public SaResult isGetDisableTime(String RoolId) {
        return SaResult.ok("当前账号剩余封禁时间：" + StpUtil.getDisableTime(RoolId));
    }

    /**
     * 账号解除封禁，
     */
    @GetMapping("/isUntieDisable")
    @ApiOperation("账号解除封禁")
    public SaResult isUntieDisable(String RoolId) {
        StpUtil.untieDisable(RoolId);
        return SaResult.ok("账号解除封禁!");
    }

    /**
     * 权限认证
     */
    @GetMapping("/doPermission")
    @ApiOperation("权限认证")
    public SaResult doPermission() {
        try {
            StpUtil.checkPermission("user-jay");
        } catch (Exception e) {
            return SaResult.error("认证失败:   " + e);
        }
        return SaResult.ok("权限认证成功！");
    }

    /**
     * 角色认证
     */
    @GetMapping("/doCheckRole")
    @ApiOperation("角色认证")
    public SaResult doCheckRole() {
        try {
            StpUtil.checkRole("jay");
        } catch (Exception e) {
            return SaResult.error("认证失败:   " + e);
        }
        return SaResult.ok("角色认证成功！");
    }

    /**
     * 账号权限集合
     */
    @GetMapping("/getPermissionList")
    @ApiOperation("账号权限集合")
    @SaCheckLogin
    public SaResult getPermissionList() {
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        List<String> roleList = stpInterfaceImpl.getPermissionList(tokenInfo.getLoginId(), tokenInfo.getLoginType());
        return SaResult.ok().setData(roleList);
    }

    /**
     * 账号角色集合
     */
    @GetMapping("/getRoleList")
    @ApiOperation("账号角色集合")
    public SaResult getRoleList() {
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        List<String> roleList = stpInterfaceImpl.getRoleList(tokenInfo.getLoginId(), tokenInfo.getLoginType());
        return SaResult.ok().setData(roleList);
    }
    /**
     * 账号角色集合
     * 不通账号系统权限登录
     */
    @GetMapping("/getRoleListUser")
    @SaCheckLogin(type = StpUserUtil.TYPE)
    @ApiOperation("user账号角色集合")
    public SaResult getRoleListUser() {
        SaTokenInfo tokenInfo = StpUserUtil.getTokenInfo();
        List<String> roleList = stpInterfaceImpl.getRoleList(tokenInfo.getLoginId(), tokenInfo.getLoginType());
        return SaResult.ok().setData(roleList);
    }


}
