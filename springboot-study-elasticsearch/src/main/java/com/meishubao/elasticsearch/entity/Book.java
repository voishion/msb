package com.meishubao.elasticsearch.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.meishubao.elasticsearch.constant.EsEntityConstant;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @author lilu
 */
@Data
@Document(indexName = EsEntityConstant.INDEX_PREFIX + "book")
public class Book {
    @Id
    @Field(type = FieldType.Text)
    private String id;

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String title;

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String author;

    @Field(type = FieldType.Double)
    private Double price;

    @Field(type = FieldType.Date, format = DateFormat.custom, pattern = EsEntityConstant.ES_DATETIME_PATTERN)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = EsEntityConstant.DATETIME_PATTERN, timezone = "GMT+8")
    @DateTimeFormat(pattern = EsEntityConstant.DATETIME_PATTERN)
    private LocalDateTime createTime;

    @Field(type = FieldType.Date, format = DateFormat.custom, pattern = EsEntityConstant.ES_DATETIME_PATTERN)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = EsEntityConstant.DATETIME_PATTERN, timezone = "GMT+8")
    @DateTimeFormat(pattern = EsEntityConstant.DATETIME_PATTERN)
    private LocalDateTime updateTime;

}
