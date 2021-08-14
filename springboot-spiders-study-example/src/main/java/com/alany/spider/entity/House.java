package com.alany.spider.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value ="t_house")
public class House {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 来源
     */
    private String sourceName;

    /**
     * item ID
     */
    private String itemId;

    /**
     * 城市
     */
    private String city;

    /**
     * 地区
     */
    private String location;

    /**
     * 详情url
     */
    private String itemUrl;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 建筑面积
     */
    private Double areaSize;

    /**
     * 单价
     */
    private Double price;

    /**
     * 市场总价
     */
    private Float marketTotalPrice;

    /**
     * 成交总价
     */
    private Float sellTotalPrice;

    /**
     * 成交状态
     */
    private String sellStatus;

    /**
     * 成交时间
     */
    private Date sellDate;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 入库时间
     */
    private Date createTime;

}