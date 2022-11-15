package com.yeweiyang.token.config;

import cn.hutool.extra.spring.SpringUtil;
import org.apache.ibatis.cache.Cache;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Jay
 * @version V1.0
 * @Package com.yeweiyang.token.config
 * @date 2022/11/15 17:20
 */
public class RedisCache implements Cache {

    /**
     * 日志
     */
    private static final Logger logger = LogManager.getLogger(RedisCache.class);

    /**
     * 读写锁
     */
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    /**
     * ID,获取缓存对象的唯一标识
     */
    private String id;

    /**
     * redisTemplate
     */
    private static RedisTemplate redisTemplate;

    public RedisCache(String id) {
        if (id == null) {
            throw new IllegalArgumentException("缓存实例需要一个id!");
        } else {
            logger.info("开启Redis缓存: id = {}", id);
            this.id = id;
        }
    }

    /**
     * 由于启动期间注入失败，只能运行期间注入
     *
     * @return
     */
    public RedisTemplate getRedisTemplate() {
        redisTemplate = (RedisTemplate<String, Object>) SpringUtil.getBean("redisTemplate");
        return redisTemplate;
    }


    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void putObject(Object key, Object value) {
        try {
            if (redisTemplate == null) {
                redisTemplate = getRedisTemplate();
            }
            logger.info("设置Redis缓存: id = {}, key = {}, value = {}", id, key, value);
            redisTemplate.opsForHash().put(this.id.toString(), key.toString(), value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object getObject(Object key) {
        try {
            if (redisTemplate == null) {
                redisTemplate = getRedisTemplate();
            }
            Object hashVal = redisTemplate.opsForHash().get(this.id.toString(), key.toString());
            logger.info("获取Redis缓存: id = {}, key = {}, hashVal = {}", id, key, hashVal);
            return hashVal;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Object removeObject(Object key) {
        try {
            if (redisTemplate == null) {
                redisTemplate = getRedisTemplate();
            }
            redisTemplate.opsForHash().delete(this.id.toString(), key.toString());
            logger.info("移除Redis缓存: id = {}, key = {}", id, key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void clear() {
        try {
            if (redisTemplate == null) {
                redisTemplate = getRedisTemplate();
            }
            redisTemplate.delete(this.id.toString());
            logger.info("清空Redis缓存: id = {}", id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getSize() {
        try {
            if (redisTemplate == null) {
                redisTemplate = getRedisTemplate();
            }
            Long size = redisTemplate.opsForHash().size(this.id.toString());
            logger.info("Redis缓存大小: id = {}, size = {}", id, size);
            return size.intValue();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return this.readWriteLock;
    }

    @Override
    public String toString() {
        return "RedisCache {" + this.id + "}";
    }
}
