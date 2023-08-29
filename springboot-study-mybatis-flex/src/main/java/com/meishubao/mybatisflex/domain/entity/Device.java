package com.meishubao.mybatisflex.domain.entity;

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

/**
 * 设备表 实体类
 *
 * @author lilu
 * @since 2023-08-29
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "设备表", description = "设备表")
@Table(value = "tb_device")
public class Device implements Serializable {

    @Id(keyType = KeyType.Auto)
    @ApiModelProperty(value = "", notes = "")
    private Long id;

    /**
     * 设备SN
     */
    @ApiModelProperty(value = "设备SN", notes = "")
    private String sn;

    /**
     * 类型ID
     */
    @ApiModelProperty(value = "类型ID", notes = "")
    private Long typeId;

    /**
     * 状态ID
     */
    @ApiModelProperty(value = "状态ID", notes = "")
    private Long statusId;

    /**
     * 扩展字段
     */
    @ApiModelProperty(value = "扩展字段", notes = "")
    private String extField;

}
