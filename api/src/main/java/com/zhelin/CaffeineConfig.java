package com.zhelin;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.zhelin.pojo.Article;
import com.zhelin.pojo.ArticleType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

// 本地缓存配置类
@Configuration
public class CaffeineConfig {

    /**
     * 声明缓存bean，所有的数据不同类型的数据都可以使用本cache
     * @return
     */
    @Bean
    public Cache<String, Article> cache() {
        return Caffeine.newBuilder()
                .initialCapacity(10)    // 初始的缓存空间大小
                .maximumSize(100)       // 最大上限缓存个数
                .build();
    }

    @Bean
    public Cache<String, List<ArticleType>> articleTypeCache() {
        return Caffeine.newBuilder()
                .initialCapacity(10)    // 初始的缓存空间大小
                .maximumSize(100)       // 最大上限缓存个数
                .build();
    }

}
