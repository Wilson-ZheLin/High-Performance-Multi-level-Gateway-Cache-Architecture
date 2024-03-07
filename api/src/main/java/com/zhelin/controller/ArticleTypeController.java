package com.zhelin.controller;

import com.github.benmanes.caffeine.cache.Cache;
import com.zhelin.pojo.Article;
import com.zhelin.pojo.ArticleType;
import com.zhelin.utils.RedisOperator;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhelin
 * @since 2024-03-06
 */
@RestController
@RequestMapping("/articleType")
public class ArticleTypeController {

    @Resource
    private Cache<String, List<ArticleType>> articleTypeCache;

    @Resource
    private RedisOperator redis;

    String articleTypeKey = "articleTypeList";

    @GetMapping("typeCache")
    public Object type_cache() {
        return articleTypeCache.getIfPresent(articleTypeKey);
    }

    @GetMapping("typeRedis")
    public Object type_redis() {
        return redis.get(articleTypeKey);
    }
}
