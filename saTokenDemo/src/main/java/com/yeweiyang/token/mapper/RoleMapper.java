package com.yeweiyang.token.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yeweiyang.token.config.RedisCache;
import com.yeweiyang.token.pojo.saToken.Role;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Jay
 * @version V1.0
 * @Package com.shanghai.test1114.mapper
 * @date 2022/1/28 11:30 上午
 */
@Mapper
@CacheNamespace(implementation = RedisCache.class,eviction = RedisCache.class)
public interface RoleMapper extends BaseMapper<Role> {
}
