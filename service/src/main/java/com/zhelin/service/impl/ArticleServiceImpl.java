package com.zhelin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhelin.mapper.ArticleMapper;
import com.zhelin.pojo.Article;
import com.zhelin.service.IArticleService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 文章资讯表 服务实现类
 * </p>
 *
 * @author zhelin
 * @since 2024-03-06
 */
@Service
public class ArticleServiceImpl implements IArticleService {

    @Resource
    private ArticleMapper articleMapper;

    @Override
    public Article queryArticleDetail(String id) {
        QueryWrapper<Article> qw = new QueryWrapper<>();
        qw.eq("id", id);

        return articleMapper.selectOne(qw);
    }

    @Override
    public void insertArticle(Article article) {
        articleMapper.insert(article);
    }

    @Override
    public void updateArticle(Article article) {
        articleMapper.updateById(article);
    }

    @Override
    public void deleteArticle(String id) {
        articleMapper.deleteById(id);
    }

    @Override
    public PageInfo<Article> queryByPage(Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Article> articles = articleMapper.selectList(new QueryWrapper<Article>().le("publish_time", LocalDateTime.now()));
        return new PageInfo<>(articles);
    }
}
