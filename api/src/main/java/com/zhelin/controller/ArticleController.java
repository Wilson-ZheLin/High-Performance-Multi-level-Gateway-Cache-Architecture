package com.zhelin.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.github.benmanes.caffeine.cache.Cache;
import com.zhelin.pojo.Article;
import com.zhelin.service.IArticleService;
import com.zhelin.utils.JsonUtils;
import com.zhelin.utils.RedisOperator;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * <p>
 * 文章资讯表 前端控制器
 * </p>
 *
 * @author zhelin
 * @since 2024-03-06
 */
@RestController
@RequestMapping("article")
public class ArticleController {

    @Resource
    private IArticleService articleService;

    @Resource
    private Cache<String, Article> cache;

    @Resource
    private RedisOperator redis;

    @GetMapping("query2")
    public Object query2(String id) {
        return articleService.queryArticleDetail(id);
    }

    /**
     * 在集群或者分布式的情况下，本地缓存受限于当前本节点，无法在分布式的环境下发挥作用
     * 固我们再次结合redis分布式缓存来进行延伸拓展
     *      当在本地缓存查询不到的情况下，先去redis中查询，如果redis中没有，则再从数据库中查询
     *      数据库中查询到值以后，再设置到redis以及本地缓存中
     *
     * @param id
     * @return
     */
    @GetMapping("query")
    public Object query(String id) {

        String articleKey = "article:" + id;
        String articleKeyRedis = "REDIS_ARTICLE:" + id;

        Article article = cache.get(articleKey, s -> {
            System.out.println("文章id为"+id+"的没有查询到，则从Redis中查询后返回...");

            Article articleReal = null;
            String articleJsonStr = redis.get(articleKeyRedis);
            // 判断从redis中查询到的文章数据是否为空
            if (StringUtils.isBlank(articleJsonStr)) {
                System.out.println("Redis中不存在该文章，将从数据库中查询...");

                // 如果为空，则进入本条件，则从数据库中查询数据
                articleReal = articleService.queryArticleDetail(id);
                // 手动把文章数据设置到redis中，后续再次查询则有值
                String articleJson = JsonUtils.objectToJson(articleReal);
                redis.set(articleKeyRedis, articleJson);
            } else {
                System.out.println("Redis中存在该文章，将直接返回...");

                // 如果不为空，则直接转换json类型article再返回即可
                articleReal = JsonUtils.jsonToPojo(articleJsonStr, Article.class);
            }
            return articleReal;
        });

        return article;
    }

    /**
     * 结合本地缓存，优化数据库的查询效率
     * 因为当在数据库表数据量达到百万甚至千万级别的时候，单条数据的查询效率会非常慢
     *
     * @param id
     * @return
     */
    @GetMapping("query3")
    public Object query3(String id) {

        String articleKey = "article:" + id;

        Article article = cache.get(articleKey, s -> {
            System.out.println("文章id为"+id+"的没有查询到，则从数据库中查询后返回...");
            return articleService.queryArticleDetail(id);
        });

        return article;
    }



    @GetMapping("insert")
    public Object insert() {

        Article article = new Article();
        article.setArticleCover("image-addre");
        article.setTitle("这是一个标题");
        article.setContent("这是一个内容");
        article.setArticleType(1);
        article.setPublishTime(LocalDateTime.now());
        article.setReadCounts(100);
        article.setCommentCounts(200);

        articleService.insertArticle(article);

        return "ok";
    }

    @GetMapping("update")
    public Object update(String id) throws InterruptedException {
        Article article = new Article();
        article.setId(id);
        article.setTitle("标题111");
        article.setContent("");

        // 0. 删除Redis内容
        String articleKeyRedis = "REDIS_ARTICLE:" + id;
        redis.del(articleKeyRedis);

        // 1. 更新数据库
        articleService.updateArticle(article);

        // 缓存双删策略
        Thread.sleep(200);
        redis.del(articleKeyRedis);

        // 2. 更新Redis
//        Article articleReal = articleService.queryArticleDetail(id);
//        String articleJson = JsonUtils.objectToJson(articleReal);
//        redis.set(articleKeyRedis, articleJson);

        return "ok";
    }

    @GetMapping("delete")
    public Object delete(String id) throws InterruptedException {
        // 0. 删除Redis内容
        String articleKeyRedis = "REDIS_ARTICLE:" + id;
        redis.del(articleKeyRedis);

        // 1. 删除数据库
        articleService.deleteArticle(id);

        // 缓存双删策略
        Thread.sleep(200);
        redis.del(articleKeyRedis);

        return "delete ok";
    }

    @GetMapping("pageList")
    public Object pageList(Integer page, Integer pageSize) {
        return articleService.queryByPage(page, pageSize);
    }

}
