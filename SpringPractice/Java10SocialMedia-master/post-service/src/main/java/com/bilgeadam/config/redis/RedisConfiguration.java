package com.bilgeadam.config.redis;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@EnableCaching
@Configuration
@EnableRedisRepositories
public class RedisConfiguration {
  //  @Value("${redis.host}")
    private String host="localhost";
   // @Value("${redis.port}")
    private int port=6379;

    @Bean
    public LettuceConnectionFactory redisConnectionFactory(){
        return new LettuceConnectionFactory(new RedisStandaloneConfiguration("localhost",6379));
    }
}
