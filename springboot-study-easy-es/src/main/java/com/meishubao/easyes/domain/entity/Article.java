package com.meishubao.easyes.domain.entity;

import lombok.Data;
import org.dromara.easyes.annotation.*;
import org.dromara.easyes.annotation.rely.Analyzer;
import org.dromara.easyes.annotation.rely.FieldStrategy;
import org.dromara.easyes.annotation.rely.FieldType;
import org.dromara.easyes.annotation.rely.IdType;

import java.time.LocalDateTime;

@Data
// 可指定分片数,副本数,若缺省则默认均为1
@IndexName(keepGlobalPrefix = true, value = "article", shardsNum = 1, replicasNum = 1)
public class Article {
    /**
     * es中的唯一id,如果你想自定义es中的id为你提供的id,比如MySQL中的id,请将注解中的type指定为customize,如此id便支持任意数据类型)
     */
    @IndexId(type = IdType.NONE)
    private String id;
    /**
     * 文章标题
     */
    @MultiIndexField(
            mainIndexField = @IndexField(fieldType = FieldType.KEYWORD),
            otherIndexFields = {
                    @InnerIndexField(suffix = "zh", fieldType = FieldType.TEXT, analyzer = Analyzer.IK_SMART, searchAnalyzer = Analyzer.IK_MAX_WORD),
                    @InnerIndexField(suffix = "pinyin", fieldType = FieldType.TEXT, analyzer = Analyzer.PINYIN, searchAnalyzer = Analyzer.IK_MAX_WORD)
            })
    private String title;
    /**
     * 文章标题
     */
    @IndexField(fieldType = FieldType.TEXT, analyzer = Analyzer.STANDARD, searchAnalyzer = Analyzer.STANDARD)
    private String titleEn;
    /**
     * 文章内容,指定了类型及存储/查询分词器
     */
    @HighLight(mappingField = "highlightContent")
    @IndexField(fieldType = FieldType.TEXT, analyzer = Analyzer.IK_SMART, searchAnalyzer = Analyzer.IK_MAX_WORD)
    private String content;
    /**
     * 作者 加@TableField注解,并指明strategy = FieldStrategy.NOT_EMPTY 表示更新的时候的策略为 创建者不为空字符串时才更新
     */
    @IndexField(strategy = FieldStrategy.NOT_EMPTY, fieldType = FieldType.TEXT, analyzer = Analyzer.IK_SMART, searchAnalyzer = Analyzer.IK_MAX_WORD)
    private String author;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
    /**
     * es中实际不存在的字段,但模型中加了,为了不和es映射,可以在此类型字段上加上 注解@TableField,并指明exist=false
     */
    @IndexField(exist = false)
    private String notExistsField;
    /**
     * 地理位置经纬度坐标 例如: "40.13933715136454,116.63441990026217"
     */
    @IndexField(fieldType = FieldType.GEO_POINT)
    private String location;
    /**
     * 图形(例如圆心,矩形)
     */
    @IndexField(fieldType = FieldType.GEO_SHAPE)
    private String geoLocation;
    /**
     * 自定义字段名称
     */
    @IndexField(value = "ext_field")
    private String customField;

    /**
     * 高亮返回值被映射的字段
     */
    private String highlightContent;

    /**
     * 匹配查询得分
     */
    @Score
    private Float searchScore;

}
