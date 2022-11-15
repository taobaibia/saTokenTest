package com.yeweiyang.token.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author Jay
 * @version V1.0
 * @Package com.yeweiyang.token.config
 * @date 2022/11/15 17:16
 */
@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory factory, Jackson2JsonRedisSerializer serializer) {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        // redis连接工厂
        template.setConnectionFactory(factory);
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        // redis.key序列化器
        template.setKeySerializer(stringRedisSerializer);
        // redis.value序列化器
        template.setValueSerializer(serializer);
        // redis.hash.key序列化器
        template.setHashKeySerializer(stringRedisSerializer);
        // redis.hash.value序列化器
        template.setHashValueSerializer(serializer);
        // 调用其他初始化逻辑
        template.afterPropertiesSet();
        // 这里设置redis事务一致
        template.setEnableTransactionSupport(true);
        return template;
    }

    /**
     * 配置redis Json序列化器
     *
     * @return
     */
    @Bean
    public Jackson2JsonRedisSerializer redisJsonSerializer() {
        // 使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值（默认使用JDK的序列化方式）
        Jackson2JsonRedisSerializer serializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        serializer.setObjectMapper(mapper);
        return serializer;
    }

}
