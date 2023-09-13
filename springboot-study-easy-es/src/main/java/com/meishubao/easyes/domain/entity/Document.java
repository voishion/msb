package com.meishubao.easyes.domain.entity;

import lombok.Data;
import org.dromara.easyes.annotation.*;
import org.dromara.easyes.annotation.rely.Analyzer;
import org.dromara.easyes.annotation.rely.FieldType;

import java.time.LocalDateTime;

@Data
@IndexName("document")
public class Document {

    /**
     * es中的唯一id
     */
    @IndexId
    private String id;
    /**
     * 文档标题
     */
    @IndexField
    private String title;
    /**
     * 文档内容
     */
    private String content;
    /**
     * 文档数量
     */
    private Integer docNumber;
    /**
     * 复合字段,此注解和SpringData中的MultiField用法类似 适用于对同一个字段通过多种分词器检索的场景
     * // 药品 中文名叫葡萄糖酸钙口服溶液 英文名叫 Calcium Gluconate 汉语拼音为 putaotangsuangaikoufurongye
     * // 用户可以通过模糊检索,例如输入 Calcium 或 葡萄糖 或 putaotang时对应药品均可以被检索到
     */
    @MultiIndexField(
            mainIndexField = @IndexField(fieldType = FieldType.KEYWORD),
            otherIndexFields = {
                    @InnerIndexField(suffix = "zh", fieldType = FieldType.TEXT, analyzer = Analyzer.IK_SMART),
                    @InnerIndexField(suffix = "pinyin", fieldType = FieldType.TEXT, analyzer = Analyzer.PINYIN)
            })
    private String multiField;
    /**
     * 复合字段英文
     */
    @HighLight(mappingField="highlightMultiFieldEn")
    @IndexField(fieldType = FieldType.TEXT, analyzer = Analyzer.IK_SMART, searchAnalyzer = Analyzer.IK_MAX_WORD)
    private String multiFieldEn;
    /**
     * 复合字段英文高亮返回值被映射的字段
     */
    private String highlightMultiFieldEn;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    // =====

    /**
     * 匹配查询得分
     */
    @Score
    private Float searchScore;

    /**
     * 按距离由近及远查询后的数据
     */
    @Distance
    private Double searchDistance;

}
