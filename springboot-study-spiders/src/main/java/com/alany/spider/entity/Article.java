package com.alany.spider.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value ="t_article")
public class Article {

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 文章编号
     */
    private String articleId;

    /**
     * 名字
     */
    private String sourceName;

    /**
     * 标题
     */
    private String title;

    /**
     * 描述
     */
    private String description;

    /**
     * 作者
     */
    private String author;

    /**
     * 地址
     */
    private String url;

    /**
     * 图片地址
     */
    private String imgUrl;

    /**
     * 类别
     */
    private String category;

    /**
     * 发布时间
     */
    private String publishTime;

    /**
     * 状态
     */
    private Integer status;

}