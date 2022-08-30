package com.example.currentlimiting.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.UUID;

@Configuration
@EnableScheduling
public class TokenBucketCreateTask {
    @Autowired
    private RedisTemplate redisTemplate;

    private Integer max = 1000;
    /**
     * 每毫秒生成一个令牌
     */
    @Scheduled(fixedRate= 1)
    private void addToken(){
        if(redisTemplate.opsForList().size("limit_list")<1000){
            redisTemplate.opsForList().rightPush("limit_list", UUID.randomUUID().toString());
        }
    }
}
