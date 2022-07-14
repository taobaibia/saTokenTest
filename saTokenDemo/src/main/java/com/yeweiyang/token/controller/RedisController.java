package com.yeweiyang.token.controller;

import com.yeweiyang.token.pojo.copy.Jay;
import com.yeweiyang.token.serivice.RedisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Jay
 * @version V1.0
 * @Package com.shanghai.test1114.controller
 * @date 2021/12/17 1:23 下午
 */
@RestController
@Api(tags = "测试:redis模块")
@RequestMapping("/redis/test/api")
public class RedisController {

    @Autowired
    private RedisService redisCacheSave;


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/v1/test/redis/desa2")
    @ApiOperation("redis业务缓存")
    public String redisTwo(@RequestParam(name = "name")String name) throws Exception {
        stringRedisTemplate.opsForValue().set(name, "业务缓存");
        System.out.println(name+"业务缓存");
        return name;
    }


    @GetMapping("/v1/redis/cache/save")
    @ApiOperation("test查看")
    public List<Jay> redisCacheSave() throws Exception {
        List<Jay> jays = redisCacheSave.RedisCacheSave();
        return jays;
    }


}
