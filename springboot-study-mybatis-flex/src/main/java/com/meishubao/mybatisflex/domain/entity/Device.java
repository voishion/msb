package com.meishubao.mybatisflex.domain.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 设备表 实体类。
 *
 * @author lilu
 * @since 2023-08-28
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("设备表")
@Table(value = "tb_device")
public class Device implements Serializable {

    @Id(keyType = KeyType.Auto)
    @ApiModelProperty("")
    private Long id;

    /**
     * 设备SN
     */
    @ApiModelProperty("设备SN")
    private String sn;

    /**
     * 类型ID
     */
    @ApiModelProperty("类型ID")
    private Long typeId;

    /**
     * 状态ID
     */
    @ApiModelProperty("状态ID")
    private Long statusId;

    /**
     * 扩展字段
     */
    @ApiModelProperty("扩展字段")
    private String extField;

}
