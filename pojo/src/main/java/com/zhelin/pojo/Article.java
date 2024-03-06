package com.zhelin.pojo;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 文章资讯表
 * </p>
 *
 * @author zhelin
 * @since 2024-03-06
 */
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章内容，长度不超过9999，需要在前后端判断
     */
    private String content;

    /**
     * 文章类型，1：图文（1张封面），2：纯文字
     */
    private Integer articleType;

    /**
     * 文章封面图，article_type=1 的时候展示
     */
    private String articleCover;

    /**
     * 用户累计点击阅读数（喜欢数）（点赞） - 放redis
     */
    private Integer readCounts;

    /**
     * 文章评论总数。评论防刷，距离上次评论需要间隔时间控制几秒
     */
    private Integer commentCounts;

    /**
     * 文章发布时间（也是预约发布的时间）
     */
    private LocalDateTime publishTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getArticleType() {
        return articleType;
    }

    public void setArticleType(Integer articleType) {
        this.articleType = articleType;
    }

    public String getArticleCover() {
        return articleCover;
    }

    public void setArticleCover(String articleCover) {
        this.articleCover = articleCover;
    }

    public Integer getReadCounts() {
        return readCounts;
    }

    public void setReadCounts(Integer readCounts) {
        this.readCounts = readCounts;
    }

    public Integer getCommentCounts() {
        return commentCounts;
    }

    public void setCommentCounts(Integer commentCounts) {
        this.commentCounts = commentCounts;
    }

    public LocalDateTime getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(LocalDateTime publishTime) {
        this.publishTime = publishTime;
    }

    @Override
    public String toString() {
        return "Article{" +
        "id = " + id +
        ", title = " + title +
        ", content = " + content +
        ", articleType = " + articleType +
        ", articleCover = " + articleCover +
        ", readCounts = " + readCounts +
        ", commentCounts = " + commentCounts +
        ", publishTime = " + publishTime +
        "}";
    }
}
