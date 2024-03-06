package com.zhelin.controller;

import com.zhelin.pojo.Article;
import com.zhelin.service.IArticleService;
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

    @GetMapping("query")
    public Object query(String id) {
        return articleService.queryArticleDetail(id);
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
    public Object update() {

        Article article = new Article();
        article.setId("1664459926468087810");
        article.setTitle("标题111");
        article.setContent("");

        articleService.updateArticle(article);

        return "ok";
    }

    @GetMapping("delete")
    public Object delete(String id) {
        articleService.deleteArticle(id);

        return "delete ok";
    }

    @GetMapping("pageList")
    public Object pageList(Integer page, Integer pageSize) {
        return articleService.queryByPage(page, pageSize);
    }

}
