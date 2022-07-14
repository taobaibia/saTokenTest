package com.yeweiyang.token.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yeweiyang.token.pojo.saToken.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Jay
 * @version V1.0
 * @Package com.shanghai.test1114.mapper
 * @date 2022/1/27 1:52 下午
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
