package com.meishubao.mybatisflex.domain.entity.tables;

import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.table.TableDef;

/**
 * 设备表 表定义层。
 *
 * @author lilu
 * @since 2023-08-28
 */
public class DeviceTableDef extends TableDef {

    /**
     * 设备表
     */
    public static final DeviceTableDef Device = new DeviceTableDef();

    
    public final QueryColumn Id = new QueryColumn(this, "id");

    /**
     * 设备SN
     */
    public final QueryColumn Sn = new QueryColumn(this, "sn");

    /**
     * 类型ID
     */
    public final QueryColumn TypeId = new QueryColumn(this, "type_id");

    /**
     * 扩展字段
     */
    public final QueryColumn ExtField = new QueryColumn(this, "ext_field");

    /**
     * 状态ID
     */
    public final QueryColumn StatusId = new QueryColumn(this, "status_id");

    /**
     * 所有字段。
     */
    public final QueryColumn AllColumns = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DefaultColumns = new QueryColumn[]{Id, Sn, TypeId, StatusId, ExtField};

    public DeviceTableDef() {
        super("", "tb_device");
    }

}
