package com.meishubao.mybatisflex.domain.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;

/**
 * 账户表 实体类
 *
 * @author lilu
 * @since 2023-08-29
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "账户表", description = "账户表")
@Table(value = "tb_account")
public class Account implements Serializable {

    /**
     * 主键
     */
    @Id(keyType = KeyType.Auto)
    @ApiModelProperty(value = "主键", notes = "")
    private Integer id;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名", notes = "")
    private String userName;

    /**
     * 年龄
     */
    @ApiModelProperty(value = "年龄", notes = "")
    private Integer age;

    /**
     * 生日
     */
    @ApiModelProperty(value = "生日", notes = "")
    private Date birthday;

    /**
     * 创建日期
     */
    @ApiModelProperty(value = "创建日期", notes = "")
    @Column(onInsertValue = "now()")
    private LocalDateTime createTime;

    /**
     * 更新日期
     */
    @ApiModelProperty(value = "更新日期", notes = "")
    @Column(onInsertValue = "now()", onUpdateValue = "now()")
    private LocalDateTime updateTime;

    /**
     * 乐观锁
     */
    @ApiModelProperty(value = "乐观锁", notes = "")
    @Column(version = true)
    private Long version;

    /**
     * 逻辑删除
     */
    @ApiModelProperty(value = "逻辑删除", notes = "")
    @Column(isLogicDelete = true)
    private Boolean deleted;

}
