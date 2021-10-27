package com.meishubao.graphqlstudy.common.dataloader;

/**
 * @author lilu
 */
public enum DataLoaderKey {
    /** 测试 **/
    Test,

    /** 轿车 **/
    Car,

    /** 部门 **/
    Department;

    /**
     * 获取key字符串
     *
     * @return
     */
    public String key() {
        return this.name();
    }

}
