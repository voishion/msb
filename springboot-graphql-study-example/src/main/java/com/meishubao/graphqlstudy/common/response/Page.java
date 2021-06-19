package com.meishubao.graphqlstudy.common.response;

import lombok.Data;

import java.util.List;

/**
 * @author lilu
 */
@Data
public abstract class Page {
    /**
     * 当前页码
     **/
    Integer number;
    /**
     * 总数据量
     **/
    Integer totalElements;
    /**
     * 总页数
     **/
    Integer totalPages;

    /**
     * 填充数据
     *
     * @param number
     * @param totalElements
     * @param totalPages
     */
    protected void fill(Integer number, Integer totalElements, Integer totalPages) {
        this.number = number;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
    }

    /**
     * 构建分页信息
     *
     * @param number        当前页码
     * @param size          显示数据量
     * @param totalElements 总数据量
     * @param content       数据内容
     * @return
     */
    public abstract Page build(Integer number, Integer size, Integer totalElements, List<?> content);
}
