package com.zhelin;

import com.github.benmanes.caffeine.cache.Cache;
import com.zhelin.pojo.ArticleType;
import com.zhelin.service.IArticleTypeService;
import com.zhelin.utils.JsonUtils;
import com.zhelin.utils.RedisOperator;
import jakarta.annotation.Resource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SysConfig implements CommandLineRunner {

    @Resource
    private IArticleTypeService articleTypeService;

    @Resource
    private Cache<String, List<ArticleType>> articleTypeCache;

    @Resource
    private RedisOperator redis;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("缓存预热。。。");

        // 1. 查询所有分类数据
        List<ArticleType> types = articleTypeService.list();
        System.out.println(types);

        String articleTypeKey = "articleTypeList";

        // 2. 设置分类数据到本地缓存
        articleTypeCache.put(articleTypeKey, types);

        // 3. 设置分类数据到redis
        redis.set(articleTypeKey, JsonUtils.objectToJson(types));

    }
}
