package com.meishubao.mybatisflex.domain.entity.tables;

import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.table.TableDef;

/**
 * 账户表 表定义层。
 *
 * @author lilu
 * @since 2023-08-28
 */
public class AccountTableDef extends TableDef {

    /**
     * 账户表
     */
    public static final AccountTableDef Account = new AccountTableDef();

    /**
     * 主键
     */
    public final QueryColumn Id = new QueryColumn(this, "id");

    /**
     * 年龄
     */
    public final QueryColumn Age = new QueryColumn(this, "age");

    /**
     * 生日
     */
    public final QueryColumn Birthday = new QueryColumn(this, "birthday");

    /**
     * 用户名
     */
    public final QueryColumn UserName = new QueryColumn(this, "user_name");

    /**
     * 所有字段。
     */
    public final QueryColumn AllColumns = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DefaultColumns = new QueryColumn[]{Id, UserName, Age, Birthday};

    public AccountTableDef() {
        super("", "tb_account");
    }

}
