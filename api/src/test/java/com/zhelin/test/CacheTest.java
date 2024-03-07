package com.zhelin.test;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.junit.Test;

public class CacheTest {

    @Test
    public void testCache() {
//        System.out.println("testCache");

        Cache<String, Object> cache = Caffeine.newBuilder().build();
        cache.put("key1", "value1");
        cache.put("key2", "value2");

        String value = cache.getIfPresent("key1").toString();
        System.out.println(value);
    }
}
