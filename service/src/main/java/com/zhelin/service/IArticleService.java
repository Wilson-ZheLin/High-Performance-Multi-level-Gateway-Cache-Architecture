package com.zhelin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.zhelin.pojo.Article;

import java.util.List;

/**
 * <p>
 * 文章资讯表 服务类
 * </p>
 *
 * @author zhelin
 * @since 2024-03-06
 */
public interface IArticleService {

    public Article queryArticleDetail(String id);

    public void insertArticle(Article article);

    public void updateArticle(Article article);

    public void deleteArticle(String id);

    public PageInfo<Article> queryByPage(Integer page, Integer pageSize);

}
