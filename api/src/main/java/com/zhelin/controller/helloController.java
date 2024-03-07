package com.zhelin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class helloController {

    @GetMapping("hello")
    public Object hello() {
        return "Hello";
    }

    @Autowired
    private StringRedisTemplate redis;

    @GetMapping("redis")
    public Object redisSet() {
        redis.opsForValue().set("Oh", "Oh~Yeah");
        return redis.opsForValue().get("Oh");
    }
}
