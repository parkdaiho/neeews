package me.parkdaiho.project.service;

import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.config.properties.RedisProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RedisService {

    private final RedisTemplate<String, String> redisTemplate;
    private final RedisProperties redisProperties;

    public void putRedisTemplate(String key, String value) {
        redisTemplate.opsForValue().set(key, value, redisProperties.getTimeout(), TimeUnit.SECONDS);
    }

    public String getValue(String key) {
        if (!redisTemplate.hasKey(key)) throw new IllegalArgumentException("Key " + key + " does not exist");

        return redisTemplate.opsForValue().get(key);
    }
}
