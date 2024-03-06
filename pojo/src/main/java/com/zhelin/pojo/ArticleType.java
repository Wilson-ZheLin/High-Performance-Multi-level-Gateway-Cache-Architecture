package com.zhelin.pojo;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhelin
 * @since 2024-03-06
 */
@TableName("article_type")
public class ArticleType implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String typeName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return "ArticleType{" +
        "id = " + id +
        ", typeName = " + typeName +
        "}";
    }
}
