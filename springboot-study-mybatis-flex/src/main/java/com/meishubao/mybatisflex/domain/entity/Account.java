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
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 账户表 实体类。
 *
 * @author lilu
 * @since 2023-08-28
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("账户表")
@Table(value = "tb_account")
public class Account implements Serializable {

    /**
     * 主键
     */
    @Id(keyType = KeyType.Auto)
    @ApiModelProperty("主键")
    private Integer id;

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String userName;

    /**
     * 年龄
     */
    @ApiModelProperty("年龄")
    private Integer age;

    /**
     * 生日
     */
    @ApiModelProperty("生日")
    private LocalDate birthday;

    /**
     * 创建日期
     */
    @ApiModelProperty("创建日期")
    @Column(onInsertValue = "now()")
    private LocalDateTime createTime;

    /**
     * 更新日期
     */
    @ApiModelProperty("更新日期")
    @Column(onInsertValue = "now()", onUpdateValue = "now()")
    private LocalDateTime updateTime;

    /**
     * 乐观锁
     */
    @ApiModelProperty("乐观锁")
    @Column(version = true)
    private Long version;

    /**
     * 逻辑删除
     */
    @Column(isLogicDelete = true)
    private Boolean deleted;

}
