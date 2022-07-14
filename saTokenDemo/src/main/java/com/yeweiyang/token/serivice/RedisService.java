package com.yeweiyang.token.serivice;

import com.yeweiyang.token.pojo.copy.Jay;

import java.util.List;

/**
 * @author Jay
 * @version V1.0
 * @Package com.shanghai.test1114.serivice
 * @date 2021/12/17 1:41 下午
 */
public interface RedisService {

    List<Jay> RedisCacheSave();
}
