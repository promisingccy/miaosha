package com.miaoshaproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * @ClassName RedisConfig
 * @Description //TODO
 * @Author ccy
 * @Date 2020/5/8 17:28
 * @Version 1.0
 **/
@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory){
        //创建redis操作对象
        RedisTemplate<String, Object>template = new RedisTemplate<>();
        // 设置 RedisConnection 工厂。它就是实现多种 Java Redis 客户端接入的秘密工厂
        template.setConnectionFactory(factory);

        // 使用 String 序列化方式，序列化 KEY
        template.setKeySerializer(RedisSerializer.string());

        // 使用 JSON 序列化方式（库是 Jackson ），序列化 VALUE
        template.setValueSerializer(RedisSerializer.json());

        return template;
    }

}
