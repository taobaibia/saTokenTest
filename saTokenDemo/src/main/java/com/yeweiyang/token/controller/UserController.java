package com.yeweiyang.token.controller;

import cn.dev33.satoken.annotation.*;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.alibaba.fastjson.JSON;
import com.yeweiyang.token.Enums.JayEnum;
import com.yeweiyang.token.Exception.AjaxJson;
import com.yeweiyang.token.Exception.CommonException;
import com.yeweiyang.token.Exception.ExceptionUtils;
import com.yeweiyang.token.config.EumeAndWeb.ResponseUtils;
import com.yeweiyang.token.config.EumeAndWeb.ResponseWeb;
import com.yeweiyang.token.mapperDto.UserDtoToMapper;
import com.yeweiyang.token.pojo.saToken.User;
import com.yeweiyang.token.request.CaseResultBackReq;
import com.yeweiyang.token.response.UserResp;
import com.yeweiyang.token.serivice.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jay
 * @version V1.0
 * @Package com.shanghai.test1114.controller
 * @date 2022/1/27 1:49 下午
 * SaTokenConfigure==注解式鉴权
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户:管理模块")
public class UserController {

    @Autowired
    private UserService userService;
//    @Autowired
//    private UserDtoToMapper userDtoToMapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 注册
     */
    @GetMapping("/doRegister")
    @ApiOperation("注册")
    public ResponseWeb<UserResp> doRegister(String username, String password) throws CommonException {
        if (StringUtils.isBlank(username) && StringUtils.isBlank(password)) {
            throw ExceptionUtils.create(JayEnum.PARAMER_VALUE_NULL);
        }
        User user = userService.doRegister(username, password);
        UserResp resp = new UserResp();
        BeanUtils.copyProperties(user, resp);
        return ResponseUtils.success(resp);
    }

    /**
     * 修改密码
     */
    @GetMapping("/doUpdate/password")
    @ApiOperation("修改密码")
    public ResponseWeb<UserResp> doUpdatePassword(String username, String password) throws CommonException {
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            throw ExceptionUtils.create(JayEnum.PARAMER_VALUE_NULL);
        }
        User user = userService.doUpdatePassword(username, password);
        UserResp resp = new UserResp();
        BeanUtils.copyProperties(user, resp);
        return ResponseUtils.success(resp);
    }

    /**
     * 删除账号
     */
    @GetMapping("/doDelete/account")
    @ApiOperation("删除账号")
    public ResponseWeb<UserResp> doDeleteAccount(String username, String password) throws CommonException {
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            throw ExceptionUtils.create(JayEnum.PARAMER_VALUE_NULL);
        }
        User user = userService.doDeleteAccount(username, password);
        UserResp resp = new UserResp();
        BeanUtils.copyProperties(user, resp);
        return ResponseUtils.success(resp);
    }

    /**
     * 所有用户
     * 若写其他架构层，service，则AOP插件鉴权
     * Sa-Token整合SpringAOP实现注解鉴权
     */

    /*
            <!-- Sa-Token整合SpringAOP实现注解鉴权 -->
            <dependency>
                <groupId>cn.dev33</groupId>
                <artifactId>sa-token-spring-aop</artifactId>
                <version>1.28.0</version>
            </dependency>
*/
    @GetMapping("/doSelect/account")
    @ApiOperation("所有用户")
//    @SaCheckLogin                           //登录认证：只有登录之后才能进入该方法
//    @SaCheckRole("admin")                   //角色认证：必须具有指定角色才能进入该方法
//    @SaCheckPermission("user-get")          //权限认证：必须具有指定权限才能进入该方法
//    @SaCheckPermission(value = {"user-add", "user-all", "user-delete"}, mode = SaMode.OR)     是否只要一个权限放行
//    @SaCheckPermission(value = "user-add", orRole = "admin")                                  权限和角色都有
//                                           orRole = {"admin", "manager", "staff"}             只有其中一个角色
//                                           orRole = {"admin, manager, staff"}                 代表必须同时具有三个角色
//    @SaCheckSafe()                          //二级认证：必须二级认证之后才能进入该方法
//    @SaCheckBasic(account = "sa:123456")    //Http Basic 认证：只有通过 Basic 认证后才能进入该方法
    public ResponseWeb<List<UserResp>> doSelectAccount() {
        List<User> user = userService.doSelectAccount();
//        List<UserResp> respList = userDtoToMapper.userToUserResp(user);
        return ResponseUtils.success(new ArrayList<>());
    }

    @SaCheckSafe
    @GetMapping("/doSelect/accountTwo")
    @ApiOperation("二级登录校验")
    public ResponseWeb<List<UserResp>> doSelectAccountTwo() {
        List<User> user = userService.doSelectAccount();
//        List<UserResp> respList = userDtoToMapper.userToUserResp(user);
        return ResponseUtils.success(new ArrayList<>());
    }

    @GetMapping("/openSafe")
    @ApiOperation("完成二级登录")
    public SaResult openSafe() {
        StpUtil.openSafe(40); // 打开二级认证，有效期为200秒
        return SaResult.ok();
    }

    // 通过Basic认证后才可以进入  ---- http://localhost:8081/at/checkBasic
    @SaCheckBasic(account = "sa:123456")
    @GetMapping("/checkBasic")
    @ApiOperation("通过Basic认证后才可以进入")
    public SaResult checkBasic() {

        // 查询value包括1000的所有token，结果集从第0条开始，返回10条
        List<String> tokenList = StpUtil.searchTokenValue("3", 0, 10);
        System.out.println("=========================所有token=========================");
        for (String token : tokenList) {
            System.out.println(token);
        }


        return SaResult.ok();
    }

    // 测试Sa-Token缓存
    @GetMapping("/testRedisAlone/login")
    @ApiOperation("Token缓存")
    public AjaxJson login(@RequestParam(defaultValue = "10001") String id) {
        System.out.println("--------------- 测试Sa-Token缓存");
        StpUtil.login(id);
        return AjaxJson.getSuccess();
    }

    // 测试业务缓存
    @GetMapping("/testRedisAlone/test")
    @ApiOperation("测试业务缓存")
    public AjaxJson test() {
        System.out.println("--------------- 测试业务缓存");
        stringRedisTemplate.opsForValue().set("hello", "Hello World");
        return AjaxJson.getSuccess();
    }

}
