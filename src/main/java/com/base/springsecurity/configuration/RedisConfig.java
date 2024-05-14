package com.base.springsecurity.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfig {

    //Tao ket noi voi server Redis
    @Bean
    public LettuceConnectionFactory redisConnection() {
       RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
       config.setHostName("localhost");
       config.setPort(6380);
       //Neu co Database
//        config.setDatabase(0);
//        config.setUsername("");
//        config.setPassword("");
       return new LettuceConnectionFactory(config);
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory redisConnection) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnection);
        return template;
    }
}

