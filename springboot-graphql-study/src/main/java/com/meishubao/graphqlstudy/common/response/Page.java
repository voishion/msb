package com.meishubao.graphqlstudy.common.response;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.PageUtil;
import graphql.com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

/**
 * @author lilu
 */
@Data
public class Page<T> {
    /**
     * 是否为空
     **/
    boolean empty;
    /**
     * 第一页
     */
    boolean first;
    /**
     * 最后一页
     */
    boolean last;
    /**
     * 当前页码
     **/
    long number;
    /**
     * 当前页数据量
     **/
    long numberOfElements;
    /**
     * 每页显示数据量
     **/
    long size;
    /**
     * 总数据量
     **/
    long totalElements;
    /**
     * 总页数
     **/
    long totalPages;
    /**
     * 内容
     **/
    List<T> content;

    /**
     * 内存分页实现
     *
     * @param pageNo   当前页码，从1开始
     * @param pageSize 页数据量
     * @param list     数据列表
     * @param <T>
     * @return
     */
    public static <T> Page<T> build(int pageNo, int pageSize, List<T> list) {
        List<T> content = ListUtil.page(pageNo - 1, pageSize, list);
        if (CollUtil.isEmpty(content)) {
            content = Lists.newArrayList();
        }

        Page<T> page = new Page<T>();
        page.setContent(content);
        page.setNumber(pageNo);
        page.setNumberOfElements(content.size());
        page.setSize(pageSize);
        page.setTotalElements(list.size());
        page.setTotalPages(PageUtil.totalPage(list.size(), pageSize));
        page.setEmpty(CollUtil.isEmpty(content));
        page.setFirst(1 == page.getNumber());
        page.setLast(page.getTotalPages() == page.getNumber());

        return page;
    }

}
