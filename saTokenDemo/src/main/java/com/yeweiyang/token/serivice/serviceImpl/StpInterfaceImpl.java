package com.yeweiyang.token.serivice.serviceImpl;

import cn.dev33.satoken.stp.StpInterface;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yeweiyang.token.mapper.*;
import com.yeweiyang.token.pojo.saToken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jay
 * @version V1.0
 * @Package com.shanghai.test1114.serivice.serviceImpl
 * @date 2022/1/21 4:20 下午
 * 自定义权限验证接口扩展
 */

@Component
@Slf4j
public class StpInterfaceImpl implements StpInterface {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UsersRolesMapper usersRolesMapper;
    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private RolePermissionKeyMapper rolePermissionKeyMapper;
    @Autowired
    private UserPermissionKeyMapper userPermissionKeyMapper;

    /**
     * 返回一个账号所拥有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        List<String> list = new ArrayList<String>();

        log.info("{账号id:   " + loginId + "   账号类型:   " + loginType + "  }");
        QueryWrapper<User> userWrapper = new QueryWrapper<>();
        userWrapper.lambda().eq(User::getUsername, loginId);
        User user = userMapper.selectOne(userWrapper);
        QueryWrapper<UserPermissionKey> rolePermissionWrapper = new QueryWrapper<>();
        rolePermissionWrapper.lambda()
                .eq(UserPermissionKey::getUserId, user.getUserId());
        List<UserPermissionKey> userPermissionKeyList = userPermissionKeyMapper.selectList(rolePermissionWrapper);
        for (UserPermissionKey userPermission : userPermissionKeyList) {
            QueryWrapper<Permission> roleWrapper = new QueryWrapper<>();
            roleWrapper.lambda()
                    .eq(Permission::getPermissionId, userPermission.getPermissionId());
            Permission roles = permissionMapper.selectOne(roleWrapper);
            list.add(roles.getPermission());
        }
        return list;
    }

    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        List<String> list = new ArrayList<String>();

        log.info("{账号id:   " + loginId + "   账号类型:   " + loginType + "  }");
        QueryWrapper<User> userWrapper = new QueryWrapper<>();
        userWrapper.lambda().eq(User::getUsername, loginId);
        User user = userMapper.selectOne(userWrapper);
        QueryWrapper<UsersRoles> usersRolesWrapper = new QueryWrapper<>();
        usersRolesWrapper.lambda()
                .eq(UsersRoles::getUserId, user.getUserId());
        List<UsersRoles> usersRolesList = usersRolesMapper.selectList(usersRolesWrapper);
        for (UsersRoles rolesId : usersRolesList) {
            QueryWrapper<Role> roleWrapper = new QueryWrapper<>();
            roleWrapper.lambda()
                    .eq(Role::getRoleId, rolesId.getRoleId());
            Role roles = roleMapper.selectOne(roleWrapper);
            list.add(roles.getName());
        }
        return list;
    }
}
