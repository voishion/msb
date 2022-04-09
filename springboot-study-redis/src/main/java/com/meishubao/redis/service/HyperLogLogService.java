package com.meishubao.redis.service;

import java.util.List;

/**
 * @author lilu
 */
public interface HyperLogLogService {

    /**
     * 添加单个元素
     *
     * @param logName
     * @param item
     * @param <T>
     */
    <T> void add(String logName, T item);

    /**
     * 将集合数据添加到 HyperLogLog
     *
     * @param logName
     * @param items
     * @param <T>
     */
    <T> void addAll(String logName, List<T> items);

    /**
     * 将 otherLogNames 的 log 合并到 logName
     *
     * @param logName       当前 log
     * @param otherLogNames 需要合并到当前 log 的其他 logs
     * @param <T>
     */
    <T> void merge(String logName, String... otherLogNames);

    /**
     * 获取统计基数
     *
     * @param logName
     * @param <T>
     * @return
     */
    <T> long count(String logName);

}
